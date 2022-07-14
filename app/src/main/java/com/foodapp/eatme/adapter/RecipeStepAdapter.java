package com.foodapp.eatme.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.foodapp.eatme.R;
import com.foodapp.eatme.model.Step;

import java.util.List;

public class RecipeStepAdapter extends RecyclerView.Adapter<RecipeStepAdapter.RecipeStepViewHolder> {
    List<Step> steps;

    public RecipeStepAdapter(List<Step> steps) {
        this.steps = steps;
    }

    @NonNull
    @Override
    public RecipeStepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_step, parent, false);
        return new RecipeStepViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecipeStepViewHolder holder, int position) {
        Step step = steps.get(position);
        holder.tvStepContent.setText(step.getStep());
        holder.tvStepNumber.setText((position + 1) + ".");
    }

    @Override
    public int getItemCount() {
        return steps != null ? steps.size() : 0;
    }

    public static class RecipeStepViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvStepNumber;
        private final TextView tvStepContent;

        public RecipeStepViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStepNumber = itemView.findViewById(R.id.tv_step_number);
            tvStepContent = itemView.findViewById(R.id.tv_step_content);
        }
    }
}
