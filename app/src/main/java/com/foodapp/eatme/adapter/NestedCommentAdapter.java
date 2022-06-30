package com.foodapp.eatme.adapter;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.foodapp.eatme.R;
import com.foodapp.eatme.clickinterface.IClickNestedComment;
import com.foodapp.eatme.model.ChildComment;
import com.foodapp.eatme.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class NestedCommentAdapter extends RecyclerView.Adapter<NestedCommentAdapter.NestedCommentViewHolder> {
    private final List<ChildComment> childCommentList;
    private final IClickNestedComment iClickNestedComment;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public NestedCommentAdapter(Map<String, ChildComment> comments, IClickNestedComment iClickNestedComment) {
        childCommentList = new ArrayList<>(comments.values());
        Collections.sort(childCommentList, Comparator.comparingLong(ChildComment::getTimestamp));
        this.iClickNestedComment = iClickNestedComment;
    }

    @NonNull
    @Override
    public NestedCommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_comment_child, parent, false);
        return new NestedCommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NestedCommentViewHolder holder, int position) {
        ChildComment comment = childCommentList.get(position);
        holder.tvContent.setText(comment.getContent());
        holder.tvUsername.setText(comment.getUsername());
        holder.tvTimeComment.setText(StringUtil.getTimeAgo(comment.getTimestamp()));
        if (comment.getUsername() != null && !comment.getUsername().trim().equals("")) {
            holder.tvReplyUsername.setText(comment.getUserReply());
        }

        holder.tvReply.setOnClickListener(view -> iClickNestedComment.onClickReplyNestedComment1(comment));
    }

    @Override
    public int getItemCount() {
        return (childCommentList != null) ? childCommentList.size() : 0;
    }

    public static class NestedCommentViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvContent;
        private final TextView tvUsername;
        private final TextView tvReply;
        private final TextView tvReplyUsername;
        private final TextView tvTimeComment;

        public NestedCommentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvReply = itemView.findViewById(R.id.tv_reply);
            tvContent = itemView.findViewById(R.id.tv_content);
            tvUsername = itemView.findViewById(R.id.tv_username);
            tvReplyUsername = itemView.findViewById(R.id.tv_reply_user);
            tvTimeComment = itemView.findViewById(R.id.tv_comment_time);
        }
    }

}
