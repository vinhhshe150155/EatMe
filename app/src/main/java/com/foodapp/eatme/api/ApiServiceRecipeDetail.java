package com.foodapp.eatme.api;

import com.foodapp.eatme.model.extend.NutriExtend;
import com.foodapp.eatme.model.extend.RecipeExtend;
import com.foodapp.eatme.util.StringUtil;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiServiceRecipeDetail {
    @Headers({
            StringUtil.RAPID_API_HOST,
            StringUtil.RAPID_API_KEY
    })
    @GET("recipes/{id}/information")
    Call<RecipeExtend> callRecipeDetails(
            @Path("id") int id
    );

    @Headers({
            StringUtil.RAPID_API_HOST,
            StringUtil.RAPID_API_KEY
    })
    @GET("recipes/{id}/nutritionWidget.json")
    Call<NutriExtend> getNutriExtend(
            @Path("id") int id
    );


}
