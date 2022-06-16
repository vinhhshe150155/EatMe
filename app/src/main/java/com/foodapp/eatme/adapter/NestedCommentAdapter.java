package com.foodapp.eatme.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.foodapp.eatme.R;
import com.foodapp.eatme.clickinterface.IClickNestedComment;
import com.foodapp.eatme.model.ChildComment;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class NestedCommentAdapter extends RecyclerView.Adapter<NestedCommentAdapter.NestedCommentViewHolder> {
    private Map<String, ChildComment> childCommentMap;
    private List<ChildComment> childCommentList;
    private final IClickNestedComment iClickNestedComment;
    public NestedCommentAdapter(Map<String, ChildComment> comments, IClickNestedComment iClickNestedComment) {
        childCommentMap = comments;
        childCommentList = new ArrayList<>(childCommentMap.values());
        Collections.sort(childCommentList, (c1, c2) -> {
            if (c1.getTimestamp() > c2.getTimestamp()) {
                return 1;
            }
            if (c1.getTimestamp() == c2.getTimestamp()) {
                return 0;
            }
            return -1;

        });
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
        if(comment.getUsername() != null && !comment.getUsername().trim().equals("")){
            holder.tvReplyUsername.setText(comment.getUserReply());
        }

        holder.tvReply.setOnClickListener(view -> iClickNestedComment.onClickReplyNestedComment1(comment));
    }

    @Override
    public int getItemCount() {
        return (childCommentList != null) ? childCommentList.size() : 0;
    }

    public class NestedCommentViewHolder extends RecyclerView.ViewHolder {
        private TextView tvContent;
        private TextView tvUsername;
        private TextView tvReply;
        private TextView tvReplyUsername;

        public NestedCommentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvReply = itemView.findViewById(R.id.tv_reply);
            tvContent = itemView.findViewById(R.id.tv_content);
            tvUsername = itemView.findViewById(R.id.tv_username);
            tvReplyUsername = itemView.findViewById(R.id.tv_reply_user);
        }
    }

}
