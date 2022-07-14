package com.foodapp.eatme.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.foodapp.eatme.R;
import com.foodapp.eatme.activity.RecipeActivity;
import com.foodapp.eatme.model.Recipe;
import com.foodapp.eatme.util.NetworkUtil;

import java.util.List;

import es.dmoral.toasty.Toasty;

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

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ListRecipeViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);
        holder.tvTitle.setText(recipe.getTitle());
        holder.tvTitle.setSelected(true);
        holder.tvReadyTime.setText(recipe.getReadyInMinutes() + "min");
        int calories = ((int) recipe.getNutrition().getNutrients().get(0).getAmount());
        holder.tvKcal.setText(calories + "kcal");
        Glide.with(context).load(recipe.getImage()).placeholder(R.drawable.placeholder_image).into(holder.imgRecipe);
        if (calories > 1200) calories = 1200;
        int colorGreen = (1200 - calories) * 190 / 1200;
        String hexColor = String.format("#%02x%02x%02x", 255, colorGreen, 50);
        holder.imgRecipeCalories.setColorFilter(Color.parseColor(hexColor));
        holder.cvRecipeItem.setOnClickListener(view -> {
            if (!NetworkUtil.isNetworkAvailable(context)) {
                Toasty.info(context, R.string.no_internet_connection, Toast.LENGTH_SHORT, true).show();
                return;
            }
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
        private final CardView cvRecipeItem;
        private final TextView tvTitle;
        private final ImageView imgRecipe;
        private final ImageView imgRecipeCalories;
        private final TextView tvReadyTime;
        private final TextView tvKcal;


        public ListRecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            cvRecipeItem = itemView.findViewById(R.id.cv_recipe);
            tvReadyTime = itemView.findViewById(R.id.tv_recipe_ready_time);
            tvTitle = itemView.findViewById(R.id.tv_recipe_item_name);
            imgRecipe = itemView.findViewById(R.id.img_recipe_item);
            tvKcal = itemView.findViewById(R.id.tv_recipe_kcal);
            imgRecipeCalories = itemView.findViewById(R.id.imageView);
        }
    }
}
