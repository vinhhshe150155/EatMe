package com.foodapp.eatme.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.foodapp.eatme.R;
import com.foodapp.eatme.activity.RecipeActivity;
import com.foodapp.eatme.model.Recipe;

import java.util.List;

public class ListRecipeAdapter extends RecyclerView.Adapter<ListRecipeAdapter.ListRecipeViewHolder> {
    private final List<Recipe> recipes;
    private final Context context;
    public ListRecipeAdapter(List<Recipe> recipes, Context context){
        this.recipes = recipes;
        this.context = context;
    }
    @NonNull
    @Override
    public ListRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recipe_layout, parent, false);
        return new ListRecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListRecipeViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);
        holder.tvRecipeName.setText(recipe.getSourceName());
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, RecipeActivity.class);
            intent.putExtra("recipe", recipe);
            context.startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public static class ListRecipeViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvRecipeName;

        public ListRecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRecipeName = itemView.findViewById(R.id.tv_recipe_name);
        }
    }
}
