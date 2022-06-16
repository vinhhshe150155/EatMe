package com.foodapp.eatme.clickinterface;

import com.foodapp.eatme.model.ChildComment;
import com.foodapp.eatme.model.Comment;


public interface IClickNestedComment {
    void onClickReplyNestedComment(ChildComment comment, Comment comment2);

    void onClickReplyNestedComment1(ChildComment comment);
}
