package com.example.demorandomrecipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.example.demorandomrecipe.adapter.RandomRecipeAdapter;
import com.example.demorandomrecipe.listeners.RandomRecipeRespondListener;
import com.example.demorandomrecipe.model.RandomRecipeAPIRes;

public class MainActivity extends AppCompatActivity {
    ProgressDialog dialog;
    ReQuestManager manager;
    RandomRecipeAdapter randomRecipeAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading...");

        manager = new ReQuestManager(this);
        manager.getRandomRecipes(randomRecipeRespondLisener);
        dialog.show();
    }

    private final RandomRecipeRespondListener randomRecipeRespondLisener = new RandomRecipeRespondListener() {
        @Override
        public void didFetch(RandomRecipeAPIRes res, String message) {
            recyclerView = findViewById(R.id.recycler_random);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,1));
            randomRecipeAdapter = new RandomRecipeAdapter(MainActivity.this, res.recipes);
            recyclerView.setAdapter(randomRecipeAdapter);

        }

        @Override
        public void didError(String message) {
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
        }
    };
}