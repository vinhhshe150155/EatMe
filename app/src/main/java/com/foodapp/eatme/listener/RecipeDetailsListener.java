package com.foodapp.eatme.listener;


import com.foodapp.eatme.model.RecipeDetailsResponse;

public interface RecipeDetailsListener {
    void didFetch(RecipeDetailsResponse response, String message);
    void didError(String message);
}
