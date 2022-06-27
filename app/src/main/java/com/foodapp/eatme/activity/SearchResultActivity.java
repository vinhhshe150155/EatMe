package com.foodapp.eatme.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.foodapp.eatme.R;
import com.foodapp.eatme.adapter.SearchRecipeAdapter;
import com.foodapp.eatme.listener.SearchRecipeResponseListener;
import com.foodapp.eatme.model.ResultApiResponse;
import com.foodapp.eatme.request.RequestManager;

import java.util.ArrayList;
import java.util.List;

public class SearchResultActivity extends AppCompatActivity {
    ProgressDialog dialog;
    RequestManager manager;
    SearchRecipeAdapter randomRecipeAdapter;
    RecyclerView recyclerView;
    SearchView searchView;
    String mquery;
    ImageView btnFilter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        dialog=new ProgressDialog(this);
        dialog.setTitle("Loading...");

        searchView= findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                manager.searchRecipes(searchRecipeResponseListener,query
                        ,"","asc", 10000000,0,10000000
                );
                mquery=query;
                dialog.show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        btnFilter = findViewById(R.id.btnFilter);
        btnFilter.setOnClickListener(this::onFilterClick);
        manager = new RequestManager(this);
        // manager.getRandomRecipes(randomRecipeResponseListener,tags);
        //dialog.show();
    }

    private void onFilterClick(View view) {
        PopupMenu popupMenu = new PopupMenu(this, btnFilter);
        // popupMenu.setOnMenuItemClickListener(this::onMenuItemClick);
        // popupMenu.inflate(R.layout.filter_search);
        popupMenu.show();
    }

    private final SearchRecipeResponseListener searchRecipeResponseListener = new SearchRecipeResponseListener() {
        @Override
        public void didFetch(ResultApiResponse response, String message) {
            dialog.dismiss();
            recyclerView=findViewById(R.id.rcvListItem);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(SearchResultActivity.this,6));
            randomRecipeAdapter = new SearchRecipeAdapter(SearchResultActivity.this,response.results);
            recyclerView.setAdapter(randomRecipeAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(SearchResultActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };
}

