package com.foodapp.eatme.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.foodapp.eatme.R;
import com.foodapp.eatme.clickinterface.IClickItemIngredient;
import com.foodapp.eatme.model.Ingredient;
import com.foodapp.eatme.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class SearchIngredientAdapter extends RecyclerView.Adapter<SearchIngredientAdapter.SearchIngredientViewHolder> {
    private final List<Ingredient> ingredients = new ArrayList<>();
    private final IClickItemIngredient iClickItemIngredient;

    public SearchIngredientAdapter(IClickItemIngredient iClickItemIngredient) {
        this.iClickItemIngredient = iClickItemIngredient;
    }

    public void setIngredientList(List<Ingredient> ingredients) {
        DiffUtil.DiffResult diffResult = DiffUtil
                .calculateDiff(new CustomDiffUtilCallBack(ingredients, this.ingredients));
        diffResult.dispatchUpdatesTo(this);
        this.ingredients.clear();
        this.ingredients.addAll(ingredients);
    }


    @NonNull
    @Override
    public SearchIngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ingredient_search, parent, false);
        return new SearchIngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchIngredientViewHolder holder, int position) {
        Ingredient ingredient = ingredients.get(position);
        holder.tvSearchIngredient.setText(StringUtil.toCaptalizedString(ingredient.getName()));
        holder.itemView.setOnClickListener(view -> iClickItemIngredient.onClickItemIngredient(ingredient));
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public static class SearchIngredientViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvSearchIngredient;

        public SearchIngredientViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSearchIngredient = itemView.findViewById(R.id.tv_search_ingredient);
        }
    }
}
