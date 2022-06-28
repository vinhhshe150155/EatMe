package com.foodapp.eatme.api;

import com.foodapp.eatme.model.extend.NutriExtend;
import com.foodapp.eatme.model.extend.RecipeExtend;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiServiceRecipeDetail {
    Gson gson = new GsonBuilder().create();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    @GET("recipes/{id}/information")
    Call<RecipeExtend> callRecipeDetails(
            @Path("id") int id,
            @Query("apiKey") String apiKey
    );


    @GET("recipes/{id}/nutritionWidget.json")
    Call<NutriExtend> getNutriExtend(
            @Path("id") int id,
            @Query("apiKey") String apiKey
    );


}
