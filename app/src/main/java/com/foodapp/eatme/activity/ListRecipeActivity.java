package com.foodapp.eatme.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.foodapp.eatme.R;
import com.foodapp.eatme.adapter.ListRecipeAdapter;
import com.foodapp.eatme.adapter.SearchRecipeAdapter;
import com.foodapp.eatme.api.ApiService;
import com.foodapp.eatme.model.ApiFoodResponse;
import com.foodapp.eatme.model.Recipe;
import com.foodapp.eatme.model.ResultApiResponse;
import com.foodapp.eatme.request.RequestManager;
import com.foodapp.eatme.util.NetworkUtil;

import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListRecipeActivity extends AppCompatActivity {
    private RecyclerView rcvListRecipe;
    private String searchIngredients, mealType;
    private List<Recipe> recipes;
    SearchRecipeAdapter randomRecipeAdapter;
//
ProgressDialog dialog;
    RequestManager manager;
    RecyclerView recyclerView;
    SearchView searchView;
    String mquery;
    ImageView btnFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        initUI();
        initData();
        searchData();
    }

    private void searchData() { dialog=new ProgressDialog(this);
        dialog.setTitle("Loading...");

        searchView= findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchIngredients = query;
                callApi(query,mealType,"");
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
//        btnFilter = findViewById(R.id.btnFilter);
//        btnFilter.setOnClickListener(this::onFilterClick);

    }

    private void initUI() {
        rcvListRecipe=findViewById(R.id.rcvListItem);
        btnFilter = findViewById(R.id.btnFilter);
        btnFilter.setOnClickListener(this::onFilterClick);
    }
    private void onFilterClick(View view) {
        PopupMenu popupMenu = new PopupMenu(this, btnFilter);
         popupMenu.setOnMenuItemClickListener(this::onMenuItemClick);
         popupMenu.inflate(R.menu.filter_search_menu);
        popupMenu.show();
    }

    public boolean onMenuItemClick(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.time:
                callApi(searchIngredients,mealType,"time");
                return true;
            case R.id.kcal:
                callApi(searchIngredients,mealType,"calories");
                return true;
        }
        return  false;
    }

    private void initData() {
        searchIngredients = getIntent().getStringExtra("ingredients");
        mealType = getIntent().getStringExtra("mealType");
        callApi(searchIngredients,mealType,"popularity");
    }


    private void callApi(String ingredient,String type, String sort) {
        if (!NetworkUtil.isNetworkAvailable(this)) {
            Toasty.normal(this, "No internet connection.").show();
            return;
        }
        ApiService.apiService.getSearchList(ingredient,100, sort,"asc",
                1000000, 0, 10000,
                true, true,type)
                .enqueue(new Callback<ResultApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<ResultApiResponse> call, @NonNull Response<ResultApiResponse> response) {
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