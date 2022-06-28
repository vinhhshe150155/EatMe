package com.foodapp.eatme.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.SearchView.OnQueryTextListener;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.foodapp.eatme.R;
import com.foodapp.eatme.adapter.ListSearchRecipeAdapter;
import com.foodapp.eatme.api.ApiService;
import com.foodapp.eatme.model.ApiFoodResponse;
import com.foodapp.eatme.model.Recipe;
import com.foodapp.eatme.util.NetworkUtil;

import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListRecipeActivity extends AppCompatActivity {
    private RecyclerView rcvListRecipe;
    private String searchIngredients;
    ConstraintLayout layoutContainer;
    private String mealType;
    private List<Recipe> recipes;
    private ProgressDialog dialog;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_recipe);
        initUI();
        initData();
        initAction();
    }


    private void initUI() {
        layoutContainer = findViewById(R.id.layout_search_recipe);
        searchView = findViewById(R.id.searchView);
        rcvListRecipe = findViewById(R.id.rcv_list_recipe);
    }

    private void initData() {
        searchIngredients = getIntent().getStringExtra("ingredients");
        mealType = getIntent().getStringExtra("mealType");
        dialog = new ProgressDialog(this);
        searchView.setQuery(searchIngredients, false);
        searchRecipe();
    }

    private void initAction() {
        searchView.setOnQueryTextListener(new OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchIngredients = query;
                closeKeyboard();
                searchRecipe();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        layoutContainer.setOnClickListener(view -> closeKeyboard());
    }


    private void searchRecipe() {
        if (!NetworkUtil.isNetworkAvailable(this)) {
            Toasty.normal(this, "No internet connection.").show();
            return;
        }
        dialog.setTitle("Loading...");
        dialog.show();
        ApiService.apiService.getSearchList(searchIngredients, 100, "popularity", "asc",
                1000000, 0, 10000,
                true, true, mealType).enqueue(new Callback<ApiFoodResponse>() {
            @Override
            public void onResponse(@NonNull Call<ApiFoodResponse> call, @NonNull Response<ApiFoodResponse> response) {
                dialog.dismiss();
                ApiFoodResponse results = response.body();
                if (results != null) {
                    recipes = results.getResults();
                    ListSearchRecipeAdapter adapter = new ListSearchRecipeAdapter(recipes, ListRecipeActivity.this);
                    rcvListRecipe.setLayoutManager(new GridLayoutManager(ListRecipeActivity.this, 2));
                    rcvListRecipe.setAdapter(adapter);
                    if (recipes.isEmpty()) {
                        Toasty.normal(ListRecipeActivity.this, "Empty list recipe.").show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ApiFoodResponse> call, @NonNull Throwable t) {
                Toasty.normal(ListRecipeActivity.this, "Error.").show();
                dialog.dismiss();
            }
        });
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}