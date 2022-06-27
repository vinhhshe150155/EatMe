package com.example.areal.Adapters;
import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.areal.Models.ModelComment;
import com.example.areal.R;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;
public class AdapterComment extends RecyclerView.Adapter<MyHolder>{
    Context context;
    List<ModelComment> list;
    String myuid;
    String postid;

    public AdapterComment(Context context, List<ModelComment> list, String myuid, String postid) {
        this.context = context;
        this.list = list;
        this.myuid = myuid;
        this.postid = postid;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.comments, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        String name = list.get(position).getUemail();
        String email = list.get(position).getUemail();
     //   String image = list.get(position).getUdp();
        final String cid = list.get(position).getcId();
        String comment = list.get(position).getComment();
//        String timestamp = list.get(position).getPtime();
//        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
//        calendar.setTimeInMillis(Long.parseLong(timestamp));
//        String timedate = DateFormat.format("dd/MM/yyyy hh:mm aa", calendar).toString();

        holder.name.setText(name);
        holder.time.setText("21/3/2001");
        holder.comment.setText(comment);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}





class MyHolder extends RecyclerView.ViewHolder {

    ImageView imagea;
    TextView name, comment, time;

    public MyHolder(@NonNull View itemView) {
        super(itemView);
      //  imagea = itemView.findViewById(R.id.loadcomment);
      //  name = itemView.findViewById(R.id.commentname);
       // comment = itemView.findViewById(R.id.commenttext);
       // time = itemView.findViewById(R.id.commenttime);
    }
}

