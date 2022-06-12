package com.example.demorandomrecipe;

import android.content.Context;

import com.example.demorandomrecipe.listeners.RandomRecipeRespondListener;
import com.example.demorandomrecipe.model.RandomRecipeAPIRes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class ReQuestManager {
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public ReQuestManager(Context context) {
        this.context = context;
    }

    public void getRandomRecipes(RandomRecipeRespondListener listener) {
        CallRandomRecipes callRandomRecipes = retrofit.create(CallRandomRecipes.class);
        Call<RandomRecipeAPIRes> call = callRandomRecipes.callRecipe(context.getString(R.string.api_key),"10");
        call.enqueue(new Callback<RandomRecipeAPIRes>() {
            @Override
            public void onResponse(Call<RandomRecipeAPIRes> call, Response<RandomRecipeAPIRes> response) {
                if (response.isSuccessful()) {
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RandomRecipeAPIRes> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }



    private interface CallRandomRecipes {
        @GET("recipes/random")
        Call<RandomRecipeAPIRes> callRecipe(
                @Query("apiKey") String apiKey,
                @Query("number") String number
        );
    }
}
