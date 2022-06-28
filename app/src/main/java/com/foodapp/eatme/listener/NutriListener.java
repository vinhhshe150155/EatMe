package com.foodapp.eatme.listener;


import com.foodapp.eatme.model.RecipeNutriResponse;

public interface NutriListener {
    void didFetch(RecipeNutriResponse response, String message);
    void didError(String message);
}
