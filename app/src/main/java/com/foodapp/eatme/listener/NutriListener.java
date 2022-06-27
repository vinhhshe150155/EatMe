package com.example.areal.Listeners;

import com.example.areal.Models.Nutrition;
import com.example.areal.Models.RecipeDetailsResponse;
import com.example.areal.Models.RecipeNutriResponse;

public interface NutriListener {
    void didFetch(RecipeNutriResponse response, String message);
    void didError(String message);
}
