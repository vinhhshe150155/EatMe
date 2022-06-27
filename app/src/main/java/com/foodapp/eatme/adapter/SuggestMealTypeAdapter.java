package com.foodapp.eatme.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.foodapp.eatme.R;
import com.foodapp.eatme.activity.ListRecipeActivity;
import com.foodapp.eatme.clickinterface.IClickItemMealType;
import com.foodapp.eatme.model.MealType;

import java.util.List;

public class SuggestMealTypeAdapter extends RecyclerView.Adapter<SuggestMealTypeAdapter.SuggestionMealTypeViewHolder> {
    private final List<MealType> mealTypes;
    private final Context context;

    public SuggestMealTypeAdapter(List<MealType> mealTypes, Context context) {
        this.mealTypes = mealTypes;
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
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, ListRecipeActivity.class);
            intent.putExtra("mealType", res.getString(mealType.getStrResource()));
            context.startActivity(intent);
        });
//        holder.itemView.setOnClickListener(view -> {
//            holder.itemView.animate()
//                    .rotationX(360)
//                    .setDuration(500)
//                    .setInterpolator(new LinearInterpolator())
//                    .setListener(new AnimatorListenerAdapter() {
//                        @Override
//                        public void onAnimationEnd(Animator animator) {
//                            holder.itemView.setRotationX(0);
//                            holder.itemView.setRotationY(0);
//                        }
//                    });
//            if (mealType.isSelected()) {
//                mealTypes.get(position).setSelected(false);
//                holder.itemView.setBackgroundResource(R.drawable.mealtype_shape);
//            } else {
//                mealTypes.get(position).setSelected(true);
//                holder.itemView.setBackgroundResource(R.drawable.selected_mealtype);
//            }
//            onClickItemMealType.onClickItemMealType(mealType);
//            Toast.makeText(context, mealType.getName(), Toast.LENGTH_SHORT).show();
//        });
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