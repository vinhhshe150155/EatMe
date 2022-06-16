package com.foodapp.eatme.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.foodapp.eatme.R;
import com.foodapp.eatme.clickinterface.IClickNestedComment;
import com.foodapp.eatme.clickinterface.IClickReplyComment;
import com.foodapp.eatme.model.ChildComment;
import com.foodapp.eatme.model.Comment;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
    private List<Comment> comments;
    Context context;
    private Map<String, ChildComment> childComments = new HashMap<>();
    private boolean isExpandable;
    private final IClickReplyComment iClickReplyComment;
    private final IClickNestedComment iClickReplyNestedComment;
    public CommentAdapter(List<Comment> comments, Context context, IClickReplyComment iClickReplyComment, IClickNestedComment iClickReplyNestedComment) {
        this.comments = comments;
        this.context = context;
        isExpandable = false;
        this.iClickReplyNestedComment = iClickReplyNestedComment;
        this.iClickReplyComment = iClickReplyComment;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_comment, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        Comment comment = comments.get(position);
        holder.tvContent.setText(comment.getContent());
        boolean isExpandable = comment.isExpandable();
        holder.layoutChild.setVisibility(isExpandable ? View.VISIBLE : View.GONE);
        if (isExpandable) {
            holder.tvViewReply.setText("Hide Reply");
        } else {
            holder.tvViewReply.setText("View Reply");
        }
        NestedCommentAdapter adapter = new NestedCommentAdapter(comment.getReply(), new IClickNestedComment() {
            @Override
            public void onClickReplyNestedComment(ChildComment comment1, Comment parent) {
                iClickReplyNestedComment.onClickReplyNestedComment(comment1, parent);
            }

            @Override
            public void onClickReplyNestedComment1(ChildComment comment1) {
                iClickReplyNestedComment.onClickReplyNestedComment(comment1, comment);
            }
        });
        holder.nestedRcv.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
        holder.nestedRcv.setHasFixedSize(true);
        holder.nestedRcv.setAdapter(adapter);
        holder.tvUsername.setText(comment.getUsername());
        holder.tvViewReply.setOnClickListener(view -> {
            comment.setExpandable(!comment.isExpandable());
            childComments = comment.getReply();
//            adapter.updateCommentList(childComments);
            notifyItemChanged(holder.getAdapterPosition());
        });
        holder.tvReply.setOnClickListener(view -> iClickReplyComment.onClickReplyComment(comment));
    }

    @Override
    public int getItemCount() {
        return comments==null?0:comments.size();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout linearLayout;
        private LinearLayout layoutChild;
        private TextView tvViewReply;
        private TextView tvContent;
        private TextView tvReply;
        private RecyclerView nestedRcv;
        private TextView tvUsername;
        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.linear_layout);
            layoutChild = itemView.findViewById(R.id.linear_list_child);
            tvViewReply = itemView.findViewById(R.id.tv_view_reply);
            nestedRcv = itemView.findViewById(R.id.rcv_comment_child);
            tvContent = itemView.findViewById(R.id.tv_content);
            tvReply = itemView.findViewById(R.id.tv_reply);
            tvUsername = itemView.findViewById(R.id.tv_username);
        }
    }
}
