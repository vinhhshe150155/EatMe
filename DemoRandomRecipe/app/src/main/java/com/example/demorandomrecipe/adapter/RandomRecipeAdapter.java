package com.example.demorandomrecipe.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demorandomrecipe.R;
import com.example.demorandomrecipe.model.Recipe;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RandomRecipeAdapter extends  RecyclerView.Adapter<RandomRecipeViewHolder> {
    Context context;
    List<Recipe> list;

    public RandomRecipeAdapter(Context context, List<Recipe> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RandomRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RandomRecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.list_random_recipe,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull RandomRecipeViewHolder holder, int position) {
        holder.tv_Tittle.setText(list.get(position).title);
        holder.tv_Tittle.setSelected(true);
        holder.tv_like.setText(list.get(position).aggregateLikes+" Likes");
        holder.tv_serving.setText(list.get(position).servings +" Servings");
        holder.tv_time.setText(list.get(position).readyInMinutes+ " minutes");
        Picasso.get().load(list.get(position).image).into(holder.img_Food);
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}

class RandomRecipeViewHolder extends RecyclerView.ViewHolder {
    CardView random_list_container;
    TextView tv_Tittle, tv_serving, tv_like,tv_time;
    ImageView img_Food;


    public RandomRecipeViewHolder(@NonNull View itemView) {
        super(itemView);
        random_list_container = itemView.findViewById(R.id.random_list_container);
        tv_Tittle  = itemView.findViewById(R.id.tv_Title);
        tv_serving  = itemView.findViewById(R.id.tv_servings);
        tv_like  = itemView.findViewById(R.id.tv_like);
        tv_time  = itemView.findViewById(R.id.tv_time);
        img_Food = itemView.findViewById(R.id.img_Food);
    }
}
