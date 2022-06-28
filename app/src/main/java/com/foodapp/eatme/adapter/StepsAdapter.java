package com.foodapp.eatme.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.foodapp.eatme.R;
import com.foodapp.eatme.model.Step;

import java.util.ArrayList;
import java.util.List;

public class StepsAdapter extends RecyclerView.Adapter<StepViewHolder> {
    Context context;
    List<Step> list;

    public StepsAdapter(Context context, ArrayList<Step> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public StepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return  new StepViewHolder(LayoutInflater.from(context).inflate(R.layout.recipe_step,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull StepViewHolder holder, int position) {
        holder.recipeStep.setText(list.get(position).getStep());
        holder.stepDetail.setText("haha");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class StepViewHolder extends RecyclerView.ViewHolder {
    TextView recipeStep;
    TextView stepDetail;


    public StepViewHolder(@NonNull View itemView) {
        super(itemView);

        recipeStep = itemView.findViewById(R.id.step);
        stepDetail = itemView.findViewById(R.id.step_Detail);

    }
}