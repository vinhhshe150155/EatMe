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
import com.foodapp.eatme.clickinterface.IClickItemDiet;
import com.foodapp.eatme.model.Diet;
import com.foodapp.eatme.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class DietAdapter extends RecyclerView.Adapter<DietAdapter.DietViewHolder> {
    private final List<Diet> diets;
    private final IClickItemDiet iClickItemDiet;
    private boolean isExpand = false;

    public DietAdapter(List<Diet> diets, IClickItemDiet iClickItemDiet) {
        Log.e("LIST", String.valueOf(diets.size()));
        this.diets = diets.subList(0, 4);
        this.iClickItemDiet = iClickItemDiet;
    }

    @NonNull

    @Override
    public DietAdapter.DietViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_filter_find_recipe, parent, false);
        return new DietAdapter.DietViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DietAdapter.DietViewHolder holder, int position) {
        Diet diet = diets.get(position);
        holder.tvDietName.setText(StringUtil.toCaptalizedString(diet.getName()));
        if (diet.isSelected()) {
            holder.itemView.setBackgroundResource(R.color.floral_white);
        } else {
            holder.itemView.setBackgroundResource(R.color.white);
        }
        holder.itemView.setOnClickListener(view -> iClickItemDiet.onClickItemDiet(diet));
    }

    @SuppressLint("NotifyDataSetChanged")
    public void onClickExpand(List<Diet> allMealTypes) {
        List<Diet> tempList = new ArrayList<>(allMealTypes);
        diets.clear();
        if (isExpand) {
            diets.addAll(tempList.subList(0, 4));
        } else {
            diets.addAll(tempList);
        }
        isExpand = !isExpand;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return diets == null ? 0 : diets.size();
    }

    static class DietViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvDietName;


        public DietViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDietName = itemView.findViewById(R.id.tv_filter_recipe);
        }
    }
}
