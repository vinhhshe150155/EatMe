package com.foodapp.eatme.api;

import android.content.Context;
import android.util.Log;

import com.foodapp.eatme.model.extend.NutriExtend;
import com.foodapp.eatme.model.extend.RecipeExtend;
import com.foodapp.eatme.util.StringUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRecipeDetailManager {
    private Context context;
    ApiServiceRecipeDetail apiServiceRecipeDetail;
    Gson gson = new GsonBuilder().create();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    public ApiRecipeDetailManager(Context context) {
        this.context = context;
        apiServiceRecipeDetail = retrofit.create(ApiServiceRecipeDetail.class);
    }

    public void getRecipeDetails(RecipeDetailsListener listener, int id) {
        Call<RecipeExtend> call = apiServiceRecipeDetail.callRecipeDetails(id, StringUtil.SPOONACULAR_API_KEY);

        call.enqueue(new Callback<RecipeExtend>() {
            @Override
            public void onResponse(Call<RecipeExtend> call, Response<RecipeExtend> response) {
                if (!response.isSuccessful()) {
                    listener.didError(response.message());
                    Log.println(Log.ERROR, "huhu32", call.toString());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RecipeExtend> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });

    }

    public void getNutriExtend(NutriListener listener, int id) {
        Call<NutriExtend> call = apiServiceRecipeDetail.getNutriExtend(id, StringUtil.SPOONACULAR_API_KEY);
        call.enqueue(new Callback<NutriExtend>() {
            @Override
            public void onResponse(Call<NutriExtend> call, Response<NutriExtend> response) {
                if (!response.isSuccessful()) {
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<NutriExtend> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

}
