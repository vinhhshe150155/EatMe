package com.foodapp.eatme.api;

import com.foodapp.eatme.model.ApiFoodResponse;
import com.foodapp.eatme.model.Ingredient;
import com.foodapp.eatme.model.ResultApiResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    Gson gson = new GsonBuilder().create();
    ApiService apiService = new Retrofit.Builder()
            .baseUrl("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @Headers({
            "X-RapidAPI-Host: spoonacular-recipe-food-nutrition-v1.p.rapidapi.com",
            "X-RapidAPI-Key: 615484898amsh2a40c4a2d671eb4p1b0769jsn1ee636a8de0b"
    })
    @GET("recipes/complexSearch")
    Call<ApiFoodResponse> getListRecipe(@Query("instructionsRequired") boolean instructionsRequired,
                                        @Query("addRecipeInformation") boolean addRecipeInformation,
                                        @Query("number") int number,
                                        @Query("includeIngredients") String includeIngredients,
                                        @Query("fillIngredients") boolean fillIngredients
    );

    @Headers({
            "X-RapidAPI-Host: spoonacular-recipe-food-nutrition-v1.p.rapidapi.com",
            "X-RapidAPI-Key: 615484898amsh2a40c4a2d671eb4p1b0769jsn1ee636a8de0b"
    })
    @GET("food/ingredients/{id}/information")
    Call<Ingredient> getIngredientById(@Path("id") int id);
    @Headers({
            "X-RapidAPI-Host: spoonacular-recipe-food-nutrition-v1.p.rapidapi.com",
            "X-RapidAPI-Key: 615484898amsh2a40c4a2d671eb4p1b0769jsn1ee636a8de0b"
    })
    @GET("recipes/complexSearch")
    Call<ResultApiResponse> getSearchList(
            @Query("includeIngredients") String includeIngredients,
            @Query("number") Integer number,
            @Query("sort") String sort,
            @Query("sortDirection") String sortDirection,
            @Query("maxReadyTime") Integer maxReadyTime,
            @Query("minCalories") Integer minCalories,
            @Query("maxCalories") Integer maxCalories,
            @Query("instructionsRequired") boolean instructionsRequired ,
            @Query("addRecipeInformation") boolean addRecipeInformation,
            @Query("type") String type

    );
}
