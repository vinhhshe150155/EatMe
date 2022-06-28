package com.foodapp.eatme.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.foodapp.eatme.R;
import com.foodapp.eatme.activity.RecipeActivity;
import com.foodapp.eatme.model.Recipe;

import java.util.List;

public class ListSearchRecipeAdapter extends RecyclerView.Adapter<ListSearchRecipeAdapter.ListRecipeViewHolder> {
    private final List<Recipe> recipes;
    private final Context context;

    public ListSearchRecipeAdapter(List<Recipe> recipes, Context context) {
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
        holder.tvTitle.setText(recipe.getTitle());
        holder.tvTitle.setSelected(true);
        holder.tvReadyTime.setText(recipe.getReadyInMinutes() + " Minutes");
        holder.tvKcal.setText(((int) recipe.getNutrition().getNutrients().get(0).getAmount()) + " Kcal");
        Glide.with(context).load(recipe.getImage()).into(holder.imgRecipe);
        holder.cvRecipeItem.setOnClickListener(view -> {
            Intent intent = new Intent(context, RecipeActivity.class);
            intent.putExtra("recipe", recipe);
            context.startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return recipes == null ? 0 : recipes.size();
    }

    public static class ListRecipeViewHolder extends RecyclerView.ViewHolder {
        private CardView cvRecipeItem;
        private TextView tvTitle;
        private ImageView imgRecipe;
        private TextView tvReadyTime;
        private TextView tvKcal;


        public ListRecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            cvRecipeItem = itemView.findViewById(R.id.cv_recipe);
            tvReadyTime = itemView.findViewById(R.id.tv_recipe_ready_time);
            tvTitle = itemView.findViewById(R.id.tv_recipe_item_name);
            imgRecipe = itemView.findViewById(R.id.img_recipe_item);
            tvKcal = itemView.findViewById(R.id.tv_recipe_kcal);
        }
    }
}
