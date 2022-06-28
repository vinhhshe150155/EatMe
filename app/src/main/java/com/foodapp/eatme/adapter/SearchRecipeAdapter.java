package com.foodapp.eatme.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.foodapp.eatme.R;
import com.foodapp.eatme.activity.RecipeDetailsActivityMain;
import com.foodapp.eatme.model.Result;


import java.util.ArrayList;
import java.util.List;

public class SearchRecipeAdapter extends RecyclerView.Adapter<SearchRecipeViewHolder>{
Context context;
List<Result> list;

    public SearchRecipeAdapter(Context context, ArrayList<Result> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SearchRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchRecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.list_random_recipe,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull SearchRecipeViewHolder holder, int position) {
        holder.textView_title.setText(list.get(position).title);
        holder.textView_title.setSelected(true);
        holder.textView_time.setText(list.get(position).readyInMinutes+ " Minutes");
        holder.textView_kcal.setText(String.valueOf((int)list.get(position).nutrition.nutrients.get(0).amount)+" Kcal");
        Glide.with(context).load(list.get(position).image).into(holder.imageView_food);
        holder.random_list_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, RecipeDetailsActivityMain.class);
                String idd = String.valueOf(list.get(holder.getAdapterPosition()).id);
                intent.putExtra("id",idd);
                context.startActivity(intent);
//                CharSequence text = "Hello toast!";
//                int duration = Toast.LENGTH_SHORT;
//
//                Toast toast = Toast.makeText(context, text, duration);
//                toast.show();



            }
        });

    }

    @Override


        public int getItemCount() {
        if (list!= null) return list.size();
        return  0;

    }
}

class SearchRecipeViewHolder extends RecyclerView.ViewHolder {
    CardView random_list_container;
    TextView textView_title;
    ImageView imageView_food;
    TextView textView_time;
    TextView  textView_kcal;

    public SearchRecipeViewHolder(@NonNull View itemView) {
        super(itemView);
        random_list_container = itemView.findViewById(R.id.list_container);
        textView_time = itemView.findViewById(R.id.tvTime);
        textView_title = itemView.findViewById(R.id.tvItemName);
        imageView_food = itemView.findViewById(R.id.imgItem);

      textView_kcal = itemView.findViewById(R.id.tvKcal);

    }
}

