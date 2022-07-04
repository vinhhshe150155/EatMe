package com.foodapp.eatme.adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.foodapp.eatme.R;
import com.foodapp.eatme.clickinterface.IClickItemMealType;
import com.foodapp.eatme.model.MealType;
import com.foodapp.eatme.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class MealTypeFilterAdapter extends RecyclerView.Adapter<MealTypeFilterAdapter.MealTypeFilterViewHolder> {
    private final List<MealType> mealTypes;
    private final IClickItemMealType iClickItemMealType;
    private boolean isExpand = false;

    public MealTypeFilterAdapter(List<MealType> mealTypes, IClickItemMealType iClickItemMealType) {
        Log.e("LIST", String.valueOf(mealTypes.size()));
        this.mealTypes = mealTypes.subList(0, 4);
        this.iClickItemMealType = iClickItemMealType;
    }

    @NonNull

    @Override
    public MealTypeFilterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_filter_find_recipe, parent, false);
        return new MealTypeFilterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealTypeFilterViewHolder holder, int position) {
        MealType mealType = mealTypes.get(position);
        holder.tvMealType.setText(StringUtil.toCaptalizedString(mealType.getName()));
        if (mealType.isSelected()) {
            holder.itemView.setBackgroundResource(R.color.floral_white);
        } else {
            holder.itemView.setBackgroundResource(R.color.white);
        }
        holder.itemView.setOnClickListener(view -> iClickItemMealType.onClickItemMealType(mealType));
    }

    @SuppressLint("NotifyDataSetChanged")
    public void onClickExpand(List<MealType> allMealTypes) {
        List<MealType> tempList = new ArrayList<>(allMealTypes);
        mealTypes.clear();
        if (isExpand) {
            mealTypes.addAll(tempList.subList(0, 4));
        } else {
            mealTypes.addAll(tempList);
        }
        isExpand = !isExpand;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return mealTypes == null ? 0 : mealTypes.size();
    }

    static class MealTypeFilterViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvMealType;

        public MealTypeFilterViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMealType = itemView.findViewById(R.id.tv_filter_recipe);
        }
    }
}