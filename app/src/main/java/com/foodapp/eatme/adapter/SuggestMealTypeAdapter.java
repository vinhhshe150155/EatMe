package com.foodapp.eatme.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.foodapp.eatme.R;
import com.foodapp.eatme.clickinterface.IClickItemMealType;
import com.foodapp.eatme.model.MealType;

import java.util.List;

public class SuggestMealTypeAdapter extends RecyclerView.Adapter<SuggestMealTypeAdapter.SuggestionMealTypeViewHolder> {
    private final List<MealType> mealTypes;
    private final Context context;
    private final IClickItemMealType iClickItemMealType;

    public SuggestMealTypeAdapter(List<MealType> mealTypes, Context context, IClickItemMealType iClickItemMealType) {
        this.mealTypes = mealTypes;
        this.iClickItemMealType = iClickItemMealType;
        this.context = context;
    }

    @NonNull
    @Override
    public SuggestionMealTypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_suggest_mealtype, parent, false);
        return new SuggestionMealTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SuggestionMealTypeViewHolder holder, int position) {
        MealType mealType = mealTypes.get(position);
        Resources res = context.getResources();
        holder.tvMealType.setText(res.getString(mealType.getStrResource()));
        holder.imgMealType.setImageResource(mealType.getImage());
        switch (position % 4) {
            case 0:
                holder.itemView.setBackgroundResource(R.drawable.green_background);
                break;
            case 1:
                holder.itemView.setBackgroundResource(R.drawable.yellow_background);

                break;
            case 2:
                holder.itemView.setBackgroundResource(R.drawable.pink_background);
                break;
            case 3:
                holder.itemView.setBackgroundResource(R.drawable.red_background);
                break;
        }
        holder.itemView.setOnClickListener(view -> iClickItemMealType.onClickItemMealType(mealType));
    }

    @Override
    public int getItemCount() {
        return mealTypes == null ? 0 : mealTypes.size();
    }

    public static class SuggestionMealTypeViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imgMealType;
        private final TextView tvMealType;

        public SuggestionMealTypeViewHolder(@NonNull View itemView) {
            super(itemView);

            imgMealType = itemView.findViewById(R.id.img_suggest_mealtype);
            tvMealType = itemView.findViewById(R.id.tv_suggest_mealtype);
        }
    }
}