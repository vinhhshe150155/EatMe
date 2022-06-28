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
import com.foodapp.eatme.model.ExtendedIngredient;
import com.squareup.picasso.Picasso;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsViewHolder> {
    Context context;
    List<ExtendedIngredient> list;

    public IngredientsAdapter(Context context, List<ExtendedIngredient> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public IngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IngredientsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_meal_ingridients,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsViewHolder holder, int position) {
        holder.textView_ingredients_name.setText(list.get(position).name);
        holder.textView_ingredients_name.setSelected(true);
        holder.texvView_ingredients_quantity.setText(list.get(position).original);
        holder.texvView_ingredients_quantity.setSelected(true);
        Picasso.get().load("https://spoonacular.com/cdn/ingredients_100x100/"+list.get(position).image).into(holder.imageView_ingredients);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class  IngredientsViewHolder extends RecyclerView.ViewHolder {
    TextView texvView_ingredients_quantity, textView_ingredients_name;
    ImageView imageView_ingredients;


    public IngredientsViewHolder(@NonNull View itemView) {
        super(itemView);
        texvView_ingredients_quantity = itemView.findViewById(R.id.texvView_ingredients_quantity);
        textView_ingredients_name = itemView.findViewById(R.id.textView_ingredients_name);
        imageView_ingredients = itemView.findViewById(R.id.imageView_ingredients);

    }
}
