package com.foodapp.eatme.adapter;

import androidx.recyclerview.widget.DiffUtil;

import com.foodapp.eatme.model.IngredientLocale;
import com.foodapp.eatme.util.LocaleHelper;

import java.util.List;

public class CustomDiffUtilCallBack extends DiffUtil.Callback {
    List<IngredientLocale> newList;
    List<IngredientLocale> oldList;
    private final String currentLanguage;

    public CustomDiffUtilCallBack(List<IngredientLocale> newList, List<IngredientLocale> oldList, String currentLanguage) {
        this.newList = newList;
        this.oldList = oldList;
        this.currentLanguage = currentLanguage;
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
        int result = -1;
        switch (currentLanguage) {
            case LocaleHelper
                    .LANG_KR:
                result = newList.get(newItemPosition).getKrName().compareTo(oldList.get(oldItemPosition).getKrName());
                break;
            case LocaleHelper
                    .LANG_EN:
                result = newList.get(newItemPosition).getEnName().compareTo(oldList.get(oldItemPosition).getEnName());
                break;
        }
        return result == 0;
    }
}
