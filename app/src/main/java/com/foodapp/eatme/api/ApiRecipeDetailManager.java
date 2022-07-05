package com.foodapp.eatme.api;

import androidx.annotation.NonNull;

import com.foodapp.eatme.model.extend.NutriExtend;
import com.foodapp.eatme.model.extend.RecipeExtend;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRecipeDetailManager {
    ApiServiceRecipeDetail apiServiceRecipeDetail;
    Gson gson = new GsonBuilder().create();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    public ApiRecipeDetailManager() {
        apiServiceRecipeDetail = retrofit.create(ApiServiceRecipeDetail.class);
    }

    public void getRecipeDetails(RecipeDetailsListener listener, int id) {
        Call<RecipeExtend> call = apiServiceRecipeDetail.callRecipeDetails(id);

        call.enqueue(new Callback<RecipeExtend>() {
            @Override
            public void onResponse(@NonNull Call<RecipeExtend> call, @NonNull Response<RecipeExtend> response) {
                if (!response.isSuccessful()) {
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(@NonNull Call<RecipeExtend> call, @NonNull Throwable t) {
                listener.didError(t.getMessage());
            }
        });

    }

    public void getNutriExtend(NutriListener listener, int id) {
        Call<NutriExtend> call = apiServiceRecipeDetail.getNutriExtend(id);
        call.enqueue(new Callback<NutriExtend>() {
            @Override
            public void onResponse(@NonNull Call<NutriExtend> call, @NonNull Response<NutriExtend> response) {
                if (!response.isSuccessful()) {
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(@NonNull Call<NutriExtend> call, @NonNull Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

}
