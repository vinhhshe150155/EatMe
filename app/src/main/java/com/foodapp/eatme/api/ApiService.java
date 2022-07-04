package com.foodapp.eatme.api;

import com.foodapp.eatme.model.ApiFoodResponse;
import com.foodapp.eatme.util.StringUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiService {
    Gson gson = new GsonBuilder().create();
    ApiService apiService = new Retrofit.Builder()
            .baseUrl("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @Headers({
            StringUtil.RAPID_API_HOST,
            StringUtil.RAPID_API_KEY
    })
    @GET("recipes/complexSearch")
    Call<ApiFoodResponse> getSearchList(
            @Query("includeIngredients") String includeIngredients,
            @Query("number") Integer number,
            @Query("diet") String diet,
            @Query("sort") String sort,
            @Query("sortDirection") String sortDirection,
            @Query("maxReadyTime") Integer maxReadyTime,
            @Query("minCalories") Integer minCalories,
            @Query("maxCalories") Integer maxCalories,
            @Query("instructionsRequired") boolean instructionsRequired,
            @Query("addRecipeInformation") boolean addRecipeInformation,
            @Query("type") String type

    );
}
