package com.foodapp.eatme.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.foodapp.eatme.R;
import com.foodapp.eatme.model.RecipeNutriResponse;

import java.util.List;

public class NutriAdapter extends RecyclerView.Adapter<NutriViewHolder> {

    Context context;
    List<RecipeNutriResponse> list;
    public NutriAdapter(Context context, List<RecipeNutriResponse> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public NutriViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull NutriViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
class  NutriViewHolder extends RecyclerView.ViewHolder {
    TextView textView_nutri_calo, textView_nutri_protein, textView_nutri_fat, textView_nutri_carb;


    public NutriViewHolder(@NonNull View itemView) {
        super(itemView);
        textView_nutri_calo = itemView.findViewById(R.id.textView_nutri_calo);
        textView_nutri_protein = itemView.findViewById(R.id.textView_nutri_protein);
        textView_nutri_fat = itemView.findViewById(R.id.textView_nutri_fat);
        textView_nutri_carb = itemView.findViewById(R.id.textView_nutri_carb);
    }
}

