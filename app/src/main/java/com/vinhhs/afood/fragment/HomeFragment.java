package com.vinhhs.afood.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.card.MaterialCardView;
import com.vinhhs.afood.ApiService;
import com.vinhhs.afood.MainActivity;
import com.vinhhs.afood.R;
import com.vinhhs.afood.adapter.SearchIngredientAdapter;
import com.vinhhs.afood.adapter.SuggestMealTypeAdapter;
import com.vinhhs.afood.model.ApiFoodResponse;
import com.vinhhs.afood.model.Ingredient;
import com.vinhhs.afood.model.MealType;
import com.vinhhs.afood.model.Recipe;
import com.vinhhs.afood.util.ListIngredient;
import com.vinhhs.afood.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    private MaterialCardView cvFindRecipe;
    private LinearLayout layoutIngredientSuggest;
    private List<MealType> mealTypes;
    private List<Ingredient> searchIngredient;
    private List<Ingredient> allIngredients;
    private RecyclerView rcvMealType;
    private RecyclerView rcvIngredientSearch;
    private SearchView svIngregient;
    private SearchIngredientAdapter searchIngredientAdapter;
    private DrawerLayout drawerLayout;
    private ConstraintLayout layoutMain;
    private MealType mealType;
    private TextView tvTitile;
    private HorizontalScrollView hrvIngredient;
    private TextView tvUsername;
    private TextView tvEmail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initUI(view);
        initData();
        bindingAction();
        return view;
    }

    private void initUI(View view) {
        tvUsername = view.findViewById(R.id.tv_username);
        tvEmail = view.findViewById(R.id.tv_email);
        cvFindRecipe = view.findViewById(R.id.card_view);
        layoutIngredientSuggest = view.findViewById(R.id.linear_ingredient_suggest);
        rcvMealType = view.findViewById(R.id.main_activity_rcv_mealtype);
        svIngregient = view.findViewById(R.id.main_sv_search_ingredients);
        drawerLayout = view.findViewById(R.id.drawerlayout);
        tvTitile = view.findViewById(R.id.tv_title);
        rcvIngredientSearch = view.findViewById(R.id.main_activity_rcv_search_ingredient);
        layoutMain = view.findViewById(R.id.layout_main);
        svIngregient.clearFocus();
        hrvIngredient = view.findViewById(R.id.layout_ingredient_suggest);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(requireContext(), DividerItemDecoration.HORIZONTAL);
        rcvIngredientSearch.addItemDecoration(itemDecoration);
        rcvIngredientSearch.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        searchIngredientAdapter = new SearchIngredientAdapter(ingredient -> {
            searchIngredient.add(ingredient);
            addIngredientView(ingredient);
            svIngregient.setQuery("", false);
            rcvIngredientSearch.setVisibility(View.GONE);
        });
        rcvIngredientSearch.setAdapter(searchIngredientAdapter);
    }

    private void initData() {
        initMealType();
        initAllIngredients();
        initRandomIngredients();
        initSuggestListIngredientLayout();
        initMealTypeRcv();
        showUser();
    }

    private void initMealTypeRcv() {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        SuggestMealTypeAdapter mealTypeAdapter = new SuggestMealTypeAdapter(mealTypes, requireContext(), mtype -> mealType = mtype);
        rcvMealType.setAdapter(mealTypeAdapter);
        rcvMealType.setLayoutManager(staggeredGridLayoutManager);
    }

    private void initSuggestListIngredientLayout() {
        for (Ingredient ingredient : searchIngredient) {
            addIngredientView(ingredient);
        }
    }

    private void addIngredientView(Ingredient ingredient) {
        View viewItem = LayoutInflater.from(requireContext()).inflate(R.layout.item_ingredient_suggestion, layoutIngredientSuggest, false);
        TextView textView = viewItem.findViewById(R.id.tv_suggest_ingredient);
        ImageView imageView = viewItem.findViewById(R.id.img_remove_ingredient_suggestion);
        textView.setText(StringUtil.toCaptalizedString(ingredient.getName()));
        imageView.setOnClickListener(view -> {
            layoutIngredientSuggest.removeView(viewItem);
            searchIngredient.remove(ingredient);
            Toast.makeText(requireContext(), Integer.toString(searchIngredient.size()), Toast.LENGTH_SHORT).show();
        });
        layoutIngredientSuggest.addView(viewItem);
    }

    private void initMealType() {
        mealTypes = new ArrayList<>();
        mealTypes.add(new MealType("main course", R.drawable.main_course, R.string.main_course));
        mealTypes.add(new MealType("soup", R.drawable.soup, R.string.soup));
        mealTypes.add(new MealType("snack", R.drawable.snack, R.string.snack));
        mealTypes.add(new MealType("appetizer", R.drawable.appetizer, R.string.appetizer));
        mealTypes.add(new MealType("dessert", R.drawable.dessert, R.string.dessert));
        mealTypes.add(new MealType("salad", R.drawable.salad, R.string.salad));
        mealTypes.add(new MealType("drink", R.drawable.drink, R.string.drink));
        mealTypes.add(new MealType("bread", R.drawable.bread, R.string.bread));
        mealTypes.add(new MealType("breakfast", R.drawable.breakfast, R.string.breakfast));
        mealTypes.add(new MealType("side dish", R.drawable.side_dish, R.string.size_dish));
        mealTypes.add(new MealType("sauce", R.drawable.sauce, R.string.sauce));
        mealTypes.add(new MealType("fingerfood", R.drawable.fingerfood, R.string.fingerfood));
    }

    private void closeKeyboard() {
        MainActivity mainActivity = (MainActivity) getActivity();
        if (mainActivity != null) {
            mainActivity.closeKeyboard();
        }
    }

    private void initRandomIngredients() {
        searchIngredient = ListIngredient.getRandomMainIngredients();
    }

    private void initAllIngredients() {
        allIngredients = ListIngredient.getAllIngredient();
    }

    private String getSearchIngredient() {
        StringBuilder sb = new StringBuilder();
        for (Ingredient ingredient : searchIngredient) {
            sb.append(ingredient.getName()).append(",");
        }
        String str = sb.toString();
        if (str.endsWith(",")) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

    private void bindingAction() {
        layoutMain.setOnClickListener(view -> closeKeyboard());
        cvFindRecipe.setOnClickListener(this::callApi);
        svIngregient.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                rcvIngredientSearch.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                rcvIngredientSearch.setVisibility(View.VISIBLE);
                return false;
            }
        });
    }

    private void filterList(String newText) {
        List<Ingredient> filterIngredientList = new ArrayList<>();
        for (Ingredient ingredient : allIngredients) {
            if (ingredient.getName().toLowerCase().contains(newText.toLowerCase())) {
                filterIngredientList.add(ingredient);
            }
        }
        if (filterIngredientList.isEmpty()) {
            Toast.makeText(requireContext(), "Empty", Toast.LENGTH_SHORT).show();
        } else {
            searchIngredientAdapter.setIngredientList(filterIngredientList);
        }
    }

    private void callApi(View view) {
        ApiService.apiService.getListRecipe(true, true, 10, getSearchIngredient(), false).enqueue(new Callback<ApiFoodResponse>() {
            @Override
            public void onResponse(@NonNull Call<ApiFoodResponse> call, @NonNull Response<ApiFoodResponse> response) {
                ApiFoodResponse results = response.body();
                List<Recipe> recipes = null;
                if (results != null) {
                    recipes = results.getResults();
                }
                if (recipes != null) {
                    Toasty.info(requireContext(), results.getResults().get(0).getTitle(), Toast.LENGTH_SHORT, true).show();
                } else {
                    Toasty.info(requireContext(), "Empty list.", Toast.LENGTH_SHORT, true).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ApiFoodResponse> call, @NonNull Throwable t) {
                Log.e("UBruhoa", t.getMessage());
            }
        });
    }

    private void showUser() {
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if (user == null) {
//            return;
//        }
//        String name = user.getDisplayName();
//        String email = user.getEmail();
//        tvUsername.setText(name);
//        tvEmail.setText(email);
    }
}