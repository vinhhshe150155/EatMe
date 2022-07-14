package com.foodapp.eatme.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.foodapp.eatme.R;
import com.foodapp.eatme.activity.RecipeActivity;
import com.foodapp.eatme.adapter.ListSavedRecipeAdapter;
import com.foodapp.eatme.dao.RecipeDatabase;
import com.foodapp.eatme.model.extend.RecipeExtend;
import com.foodapp.eatme.util.NetworkUtil;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;


public class ListRecipeFragment extends Fragment {
    RecipeDatabase database;
    List<RecipeExtend> recipeList;
    RecyclerView rcvListSavedRecipe;
    ImageView imgMenu;
    DrawerLayout drawerLayout;
    FirebaseUser user;
    LinearLayout layoutEmpty;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_recipe, container, false);
        initUI(view);
        initData();
        initAction();
        return view;
    }


    private void initUI(View view) {
        if (getActivity() != null) {
            drawerLayout = getActivity().findViewById(R.id.drawerlayout);
        }
        layoutEmpty = view.findViewById(R.id.layout_empty_saved_recipe);
        layoutEmpty.setVisibility(View.GONE);
        imgMenu = view.findViewById(R.id.img_home_menu);
        rcvListSavedRecipe = view.findViewById(R.id.rcv_list_saved_recipe);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        rcvListSavedRecipe.setLayoutManager(linearLayoutManager);
    }

    private void initAction() {
        imgMenu.setOnClickListener(view -> drawerLayout.openDrawer(GravityCompat.START));
    }

    private void initData() {
        database = RecipeDatabase.getInstance(requireActivity().getApplicationContext());
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            return;
        }
        if (NetworkUtil.isNetworkAvailable(requireContext())) {
            fetchRecipeFirebase();
        } else {
            recipeList = database.recipeDAO().getAllRecipe();
            initRecyclerView();
        }
    }

    private void initRecyclerView() {
        if (recipeList == null || recipeList.isEmpty()) {
            layoutEmpty.setVisibility(View.VISIBLE);
            return;
        }
        if (context != null) {
            ListSavedRecipeAdapter adapter = new ListSavedRecipeAdapter(recipeList, context,
                    recipe -> {
                        Intent intent = new Intent(requireContext(), RecipeActivity.class);
                        intent.putExtra("recipe", recipe);
                        startActivity(intent);
                    }, this::deleteRecipeFirebase);
            rcvListSavedRecipe.setAdapter(adapter);
        }
    }

    private void fetchRecipeFirebase() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            return;
        }
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("user").child(user.getUid()).child("savedRecipe");
        ref.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                recipeList = new ArrayList<>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    RecipeExtend recipe = postSnapshot.getValue(RecipeExtend.class);
                    if (recipe != null) {
                        recipeList.add(recipe);
                    }
                }
                List<RecipeExtend> deleteList = database.recipeDAO().getAllRecipe();
                for (RecipeExtend recipeExtend : deleteList) {
                    database.recipeDAO().delete(recipeExtend.getId());
                }
                for (RecipeExtend recipe : recipeList) {
                    database.recipeDAO().insert(recipe);
                }
                initRecyclerView();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("list recipe", "loadPost:onCancelled", databaseError.toException());
            }
        });
    }

    private void deleteRecipeFirebase(RecipeExtend recipe) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            return;
        }
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("user").child(user.getUid()).child("savedRecipe");
        Query deleteQuery = ref.orderByChild("id").equalTo(recipe.getId());
        deleteQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot recipeSnapshot : dataSnapshot.getChildren()) {
                    recipeSnapshot.getRef().removeValue();
                    Toasty.custom(requireContext(), R.string.deleted, R.drawable.ic_delete, R.color.violet, Toast.LENGTH_SHORT, true, true).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toasty.error(requireContext(), R.string.error).show();
            }
        });
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.context = null;
    }
}