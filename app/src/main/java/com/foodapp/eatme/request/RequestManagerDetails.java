package com.example.areal;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.areal.Listeners.NutriListener;
import com.example.areal.Listeners.RandomRecipeResponseListener;
import com.example.areal.Listeners.RecipeDetailsListener;
import com.example.areal.Listeners.StepsListener;
import com.example.areal.Models.Nutrient;
import com.example.areal.Models.Nutrition;
import com.example.areal.Models.RandomRecipeApiResponse;
import com.example.areal.Models.RecipeDetailsResponse;
import com.example.areal.Models.RecipeNutriResponse;
import com.example.areal.Models.StepsApiResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class RequestManagerDetails {
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManagerDetails(Context context) {
        this.context = context;
    }
    public void getRandomRecipes(RandomRecipeResponseListener listener, List<String> tags){
        CallRandomRecipes callRandomRecipes = retrofit.create(CallRandomRecipes.class);
        Call<RandomRecipeApiResponse> call = callRandomRecipes.callRandomRecipe(context.getString(R.string.api_key),"10",tags);
        call.enqueue(new Callback<RandomRecipeApiResponse>() {
            @Override
            public void onResponse(Call<RandomRecipeApiResponse> call, Response<RandomRecipeApiResponse> response) {
                if (!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RandomRecipeApiResponse> call, Throwable t) {
                listener.didError(t.getMessage());

            }
        });
    }

    // get recipe details
    public void getRecipeDetails(RecipeDetailsListener listener, int id){
        CallRecipeDetail callRecipeDetail = retrofit.create(CallRecipeDetail.class);
        Call<RecipeDetailsResponse> call = callRecipeDetail.callRecipeDetails(id, context.getString(R.string.api_key));
       // Log.println(Log.ERROR,"hu2",call..toString());
        call.enqueue(new Callback<RecipeDetailsResponse>() {
            @Override
            public void onResponse(Call<RecipeDetailsResponse> call, Response<RecipeDetailsResponse> response) {
                if (!response.isSuccessful()){
                    listener.didError(response.message());
                    Log.println(Log.ERROR,"huhu32",call.toString());

                    return;
                }
                Log.println(Log.ERROR,"huhu2",call.toString());

                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RecipeDetailsResponse> call, Throwable t) {
               // Log.println(Log.ERROR,"huhu2",call.toString());
                listener.didError(t.getMessage());
            }
        });
    }
    // get recipe details with nutrition
    public void getRecipeDetailsWithNutri(NutriListener listener, int id){
        CallRecipeDetailWithNutri callRecipeDetailWithNutri = retrofit.create(CallRecipeDetailWithNutri.class);
        Call<RecipeNutriResponse> call = callRecipeDetailWithNutri.callRecipeDetailsNutri(id,context.getString(R.string.api_key));
        CallRecipeDetail callRecipeDetail = retrofit.create(CallRecipeDetail.class);
      //  Call<RecipeDetailsResponse> call = callRecipeDetail.callRecipeDetails(id, context.getString(R.string.api_key));
        call.enqueue(new Callback<RecipeNutriResponse>() {
            @Override
            public void onResponse(Call<RecipeNutriResponse> call, Response<RecipeNutriResponse> response) {
                if (!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RecipeNutriResponse> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    // get recipe steps
    public void getRecipeStep (StepsListener listener, int id){
        Log.println(Log.ERROR,"thu1","th1");
        CallRecipeSteps callRecipeSteps = retrofit.create(CallRecipeSteps.class);
        Call<StepsApiResponse> call = callRecipeSteps.callRecipeStep(id,context.getString(R.string.api_key));


        call.enqueue(new Callback<StepsApiResponse>() {
            @Override
            public void onResponse(Call<StepsApiResponse> call, Response<StepsApiResponse> response) {
                if (!response.isSuccessful()){

                    listener.didError(response.message());

                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<StepsApiResponse> call, Throwable t) {

                listener.didError(t.getMessage());



            }
        });
    }
//    public void getSteps ()

    private interface CallRandomRecipes{
        @GET("recipes/random")
        Call<RandomRecipeApiResponse> callRandomRecipe(
            @Query("apiKey") String apiKey,
            @Query("number") String number,
            @Query("tags") List<String> tags
        );

    }
    private  interface  CallRecipeDetail{
        @GET("recipes/{id}/information")
        Call<RecipeDetailsResponse> callRecipeDetails(
                @Path("id") int id,
                @Query("apiKey") String apiKey
        );
    }

    private interface CallRecipeSteps {
        @GET("recipes/{id}/analyzedInstructions")
        Call<StepsApiResponse> callRecipeStep(
                @Path("id") int id,
                @Query("apiKey") String apiKey
        );
    }
    private  interface  CallRecipeDetailWithNutri{
        @GET("recipes/{id}/nutritionWidget.json")
        Call<RecipeNutriResponse> callRecipeDetailsNutri(
                @Path("id") int id,
                @Query("apiKey") String apiKey
        );
    }




}
