package com.foodapp.eatme.api;

import com.foodapp.eatme.model.extend.RecipeExtend;

public interface RecipeDetailsListener {
    void didFetch(RecipeExtend response, String message);

    void didError(String message);
}
