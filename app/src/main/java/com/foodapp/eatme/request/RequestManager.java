package com.foodapp.eatme.request;


import android.content.Context;


import com.foodapp.eatme.R;
import com.foodapp.eatme.listener.SearchRecipeResponseListener;
import com.foodapp.eatme.model.ResultApiResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RequestManager {
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context) {
        this.context = context;
    }

        public void searchRecipes(SearchRecipeResponseListener listener, String query
                , String sort, String sortDirection,
                                  Integer maxReadyTime,
                                  Integer minCalories, Integer maxCalories
        ){
        SearchRecipes callRandomRecipes = retrofit.create(SearchRecipes.class);
        Call<ResultApiResponse> call = callRandomRecipes.callRandomRecipe("b21f3e6cb22b45809bf585719c5a6dbf", query,
                sort,sortDirection,
               maxReadyTime,minCalories,maxCalories,true,true
        );
        call.enqueue(new Callback<ResultApiResponse>() {
            @Override
            public void onResponse(Call<ResultApiResponse> call, Response<ResultApiResponse> response) {
                if (!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<ResultApiResponse> call, Throwable t) {
                listener.didError(t.getMessage());
            }

        });
    }

//    public void searchRecipes(SearchRecipeResponseListener listener, String query,
//                              String sort, String sortDirection,
//                              Integer maxReadyTime,
//                              Integer minCalories, Integer maxCalories
//    ){
//        SearchRecipes callRandomRecipes = retrofit.create(SearchRecipes.class);
//        Call<RandomRecipeApiResponse> call = callRandomRecipes.callRandomRecipe(context.getString(R.string.api_key),
//                query,sort,sortDirection,
//                maxReadyTime,minCalories,maxCalories,true,true);
//        call.enqueue(new Callback<RandomRecipeApiResponse>() {
//            @Override
//            public void onResponse(Call<RandomRecipeApiResponse> call, Response<RandomRecipeApiResponse> response) {
//                if (!response.isSuccessful()){
//                    listener.didError(response.message());
//                    return;
//                }
//                listener.didFetch(response.body(), response.message());
//            }
//
//            @Override
//            public void onFailure(Call<RandomRecipeApiResponse> call, Throwable t) {
//                listener.didError(t.getMessage());
//
//            }
//        });
//    }
//    private interface CallRandomRecipes{
//        @GET("recipes/random")
//        Call<RandomRecipeApiResponse> callRandomRecipe(
//                @Query("apiKey") String apiKey,
//                @Query("number") String number,
//                @Query("tags") List<String> tags
//
//        );
//
//    }
private interface SearchRecipes{
    @GET("recipes/complexSearch")
    Call<ResultApiResponse> callRandomRecipe(
            @Query("apiKey") String apiKey,
            @Query("includeIngredients") String includeIngredients,
            @Query("sort") String sort,
            @Query("sortDirection") String sortDirection,
            @Query("maxReadyTime") Integer maxReadyTime,
            @Query("minCalories") Integer minCalories,
            @Query("maxCalories") Integer maxCalories,
            @Query("instructionsRequired") boolean instructionsRequired ,
            @Query("addRecipeInformation") boolean addRecipeInformation

    );

    }
    private interface GetRecipes{
        @GET("recipes/complexSearch")
        Call<ResultApiResponse> getRecipe(
                @Query("apiKey") String apiKey,
                @Query("query") String query,
                @Query("sort") String sort,
                @Query("sortDirection") String sortDirection,
                @Query("maxReadyTime") Integer maxReadyTime,
                @Query("minCalories") Integer minCalories,
                @Query("maxCalories") Integer maxCalories,
                @Query("instructionsRequired") boolean instructionsRequired ,
                @Query("addRecipeInformation") boolean addRecipeInformation
        );

    }
}
