package com.foodapp.eatme.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.foodapp.eatme.R;
import com.foodapp.eatme.clickinterface.IClickDeleteSavedRecipe;
import com.foodapp.eatme.clickinterface.IClickItemSavedRecipe;
import com.foodapp.eatme.dao.RecipeDatabase;
import com.foodapp.eatme.model.extend.RecipeExtend;

import java.util.List;

public class ListSavedRecipeAdapter extends RecyclerView.Adapter<ListSavedRecipeAdapter.ListSavedRecipeViewHolder> {
    private final List<RecipeExtend> recipes;
    private final ViewBinderHelper viewBinderHelper = new ViewBinderHelper();
    private final Context context;
    private final IClickItemSavedRecipe iClickItemSavedRecipe;
    private final IClickDeleteSavedRecipe iClickDeleteSavedRecipe;
    RecipeDatabase database;

    public ListSavedRecipeAdapter(List<RecipeExtend> recipes, Context context, IClickItemSavedRecipe iClickItemSavedRecipe, IClickDeleteSavedRecipe iClickDeleteSavedRecipe) {
        this.recipes = recipes;
        this.context = context;
        viewBinderHelper.setOpenOnlyOne(true);
        this.iClickItemSavedRecipe = iClickItemSavedRecipe;
        this.iClickDeleteSavedRecipe = iClickDeleteSavedRecipe;
        database = RecipeDatabase.getInstance(context.getApplicationContext());
    }

    @NonNull
    @Override
    public ListSavedRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_saved_recipe, parent, false);
        return new ListSavedRecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListSavedRecipeViewHolder holder, int position) {
        RecipeExtend recipe = recipes.get(position);
        viewBinderHelper.bind(holder.swipeRevealLayout, String.valueOf(recipe.getId()));
        holder.tvRecipeName.setText(recipe.getTitle());
        holder.tvCalories.setText((recipe.getNutriExtend().getCalories().replace("k", "")));
        holder.tvReadyMinute.setText(String.valueOf(recipe.getReadyInMinutes()));
        Glide.with(context)
                .load(recipe.getImage())
                .centerCrop()
                .into(holder.imgRecipe);
        holder.layoutItemSavedRecipe.setOnClickListener(view -> iClickItemSavedRecipe.onClickItemSavedRecipe(recipe));
        holder.layoutDelete.setOnClickListener(view -> {
            recipes.remove(holder.getAdapterPosition());
            notifyItemRemoved(holder.getAdapterPosition());
            deleteRecipe(recipe.getId());
            iClickDeleteSavedRecipe.onClickDeleteSavedRecipe(recipe);
        });
    }


    private void deleteRecipe(int id) {
        database.recipeDAO().delete(id);
    }

    @Override
    public int getItemCount() {
        return recipes == null ? 0 : recipes.size();
    }

    public static class ListSavedRecipeViewHolder extends RecyclerView.ViewHolder {
        private final SwipeRevealLayout swipeRevealLayout;
        private final LinearLayout layoutDelete;
        private final TextView tvRecipeName;
        private final ConstraintLayout layoutItemSavedRecipe;
        private final TextView tvCalories;
        private final TextView tvReadyMinute;
        private final ImageView imgRecipe;

        public ListSavedRecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            swipeRevealLayout = itemView.findViewById(R.id.swipe_recipe_layout);
            layoutDelete = itemView.findViewById(R.id.layout_delete_recipe);
            tvRecipeName = itemView.findViewById(R.id.tv_recipe_name);
            layoutItemSavedRecipe = itemView.findViewById(R.id.layout_saved_recipe);
            tvCalories = itemView.findViewById(R.id.tv_recipe_calories);
            tvReadyMinute = itemView.findViewById(R.id.tv_recipe_ready_minute);
            imgRecipe = itemView.findViewById(R.id.img_save_recipe);
        }
    }

}