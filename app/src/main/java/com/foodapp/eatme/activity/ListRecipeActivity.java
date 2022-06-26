package com.foodapp.eatme.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.foodapp.eatme.R;
import com.foodapp.eatme.adapter.ListRecipeAdapter;
import com.foodapp.eatme.adapter.SearchRecipeAdapter;
import com.foodapp.eatme.api.ApiService;
import com.foodapp.eatme.model.ApiFoodResponse;
import com.foodapp.eatme.model.Recipe;
import com.foodapp.eatme.model.ResultApiResponse;
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
    SearchRecipeAdapter randomRecipeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_list_recipe);
        setContentView(R.layout.activity_search_result);
        initUI();
        initData();

    }

    private void initUI() {
        rcvListRecipe=findViewById(R.id.rcvListItem);
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
        ApiService.apiService.getSearchList(searchIngredients,"random", "asc", 1000000, 0, 10000, true, true).enqueue(new Callback<ResultApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<ResultApiResponse> call, @NonNull Response<ResultApiResponse> response) {
                rcvListRecipe.setHasFixedSize(true);
                randomRecipeAdapter = new SearchRecipeAdapter(ListRecipeActivity.this,response.body().results);
                rcvListRecipe.setAdapter(randomRecipeAdapter);
                rcvListRecipe.setLayoutManager(new GridLayoutManager(ListRecipeActivity.this,2));
                }


            @Override
            public void onFailure(@NonNull Call<ResultApiResponse> call, @NonNull Throwable t) {
                Log.e("UBruhoa", t.getMessage());
            }
        });
    }
}