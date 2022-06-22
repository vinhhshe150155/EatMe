package com.foodapp.eatme.listener;


import com.foodapp.eatme.model.ResultApiResponse;

public interface SearchRecipeResponseListener {
    void didFetch(ResultApiResponse response, String message);
    void  didError(String message);
}
