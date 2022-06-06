package com.vinhhs.afood.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vinhhs.afood.R;
import com.vinhhs.afood.foodinterface.IClickItemMealType;
import com.vinhhs.afood.model.MealType;
import com.vinhhs.afood.util.StringUtil;

import java.util.List;

public class SuggestMealTypeAdapter extends RecyclerView.Adapter<SuggestMealTypeAdapter.SuggestionMealTypeViewHolder> {
    private final List<MealType> mealTypes;
    private final Context context;
    private final IClickItemMealType onClickItemMealType;

    public SuggestMealTypeAdapter(List<MealType> mealTypes, Context context, IClickItemMealType onClickItemMealType) {
        this.mealTypes = mealTypes;
        this.context = context;
        this.onClickItemMealType = onClickItemMealType;
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
        holder.itemView.setOnClickListener(view -> {
            holder.itemView.animate()
                    .rotationX(360)
                    .setDuration(500)
                    .setInterpolator(new LinearInterpolator())
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animator) {
                            holder.itemView.setRotationX(0);
                            holder.itemView.setRotationY(0);
                        }
                    });
            if (mealType.isSelected()) {
                mealTypes.get(position).setSelected(false);
                holder.itemView.setBackgroundResource(R.drawable.mealtype_shape);
            } else {
                mealTypes.get(position).setSelected(true);
                holder.itemView.setBackgroundResource(R.drawable.selected_mealtype);
            }
            onClickItemMealType.onClickItemMealType(mealType);
            Toast.makeText(context, mealType.getName(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return mealTypes.size();
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