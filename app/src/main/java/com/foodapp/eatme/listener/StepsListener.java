package com.example.areal.Listeners;

import com.example.areal.Models.RecipeNutriResponse;
import com.example.areal.Models.StepsApiResponse;

public interface StepsListener {
    void didFetch(StepsApiResponse response, String message);
    void didError(String message);
}
