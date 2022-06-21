package com.foodapp.eatme.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.foodapp.eatme.R;
import com.foodapp.eatme.adapter.ListRecipeAdapter;
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
    private List<Recipe> recipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_recipe);
        initUI();
        initData();

    }

    private void initUI() {
        rcvListRecipe = findViewById(R.id.rcv_list_recipe);
    }

    private void initData() {
        searchIngredients = getIntent().getStringExtra("ingredients");
        callApi();
    }

    private void callApi() {
        if (!NetworkUtil.isNetworkAvailable(this)) {
            Toasty.normal(this, "No internet connection.").show();
            return;
        }
        ApiService.apiService.getListRecipe(true, true, 10, searchIngredients, false).enqueue(new Callback<ApiFoodResponse>() {
            @Override
            public void onResponse(@NonNull Call<ApiFoodResponse> call, @NonNull Response<ApiFoodResponse> response) {
                ApiFoodResponse results = response.body();
                if (results != null) {
                    recipes = results.getResults();
                    ListRecipeAdapter adapter = new ListRecipeAdapter(recipes, ListRecipeActivity.this);
                    rcvListRecipe.setLayoutManager(new GridLayoutManager(ListRecipeActivity.this, 2));
                    rcvListRecipe.setAdapter(adapter);
                    if(recipes.isEmpty()){
                        Toasty.normal(ListRecipeActivity.this, "Empty list recipe.").show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ApiFoodResponse> call, @NonNull Throwable t) {
                Log.e("UBruhoa", t.getMessage());
            }
        });
    }
}