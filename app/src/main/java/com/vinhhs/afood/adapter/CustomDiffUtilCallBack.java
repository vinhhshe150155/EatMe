package com.vinhhs.afood.adapter;

import androidx.recyclerview.widget.DiffUtil;

import com.vinhhs.afood.model.Ingredient;

import java.util.List;

public class CustomDiffUtilCallBack extends DiffUtil.Callback {
    List<Ingredient> newList;
    List<Ingredient> oldList;

    public CustomDiffUtilCallBack(List<Ingredient> newList, List<Ingredient> oldList) {
        this.newList = newList;
        this.oldList = oldList;
    }

    @Override
    public int getOldListSize() {
        return oldList != null ? oldList.size() : 0;
    }

    @Override
    public int getNewListSize() {
        return newList != null ? newList.size() : 0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return newList.get(newItemPosition).getId() == oldList.get(oldItemPosition).getId();

    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        int result = newList.get(newItemPosition).getName().compareTo(oldList.get(oldItemPosition).getName());
        return result == 0;
    }
}
