package com.foodapp.eatme.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.foodapp.eatme.R;
import com.foodapp.eatme.model.extend.ExtendedIngredient;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {
    Context context;
    List<ExtendedIngredient> ingredients;

    public IngredientAdapter(Context context, List<ExtendedIngredient> ingredients) {
        this.context = context;
        this.ingredients = ingredients;
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IngredientViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recipe_detail_ingredient, parent, false));

    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        ExtendedIngredient ingredient = ingredients.get(position);
        holder.tvIngredientName.setText(validateName(ingredient.getName()));
        holder.tvIngredientName.setSelected(true);
        holder.tvIngredientAmount.setText(String.format("%.1f", ingredient.getAmount()) + " " + ingredient.getUnit());
        holder.tvIngredientAmount.setSelected(true);
        Glide.with(context).load("https://spoonacular.com/cdn/ingredients_100x100/" + ingredient.getImage()).placeholder(R.drawable.placeholder_image).into(holder.imgIngredient);
    }

    public String validateName(String str) {
        StringBuilder rs = new StringBuilder();
        if (str == null) {
            return rs.toString();
        }
        int i = 0;
        do {
            char c = str.charAt(i);
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c == ' ')) {
                rs.append(c);
            }
            i++;
        } while (i < str.length());
        return rs.toString().trim();
    }

    @Override
    public int getItemCount() {
        return ingredients == null ? 0 : ingredients.size();
    }

    static class IngredientViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvIngredientName;
        private final TextView tvIngredientAmount;
        private final ImageView imgIngredient;

        public IngredientViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIngredientName = itemView.findViewById(R.id.tv_recipe_detail_ingredient_name);
            tvIngredientAmount = itemView.findViewById(R.id.tv_recipe_detail_ingredient_amount);
            imgIngredient = itemView.findViewById(R.id.img_recipe_detail_ingredient);
        }
    }
}
