package com.foodapp.eatme.listener;


import com.foodapp.eatme.model.StepsApiResponse;

public interface StepsListener {
    void didFetch(StepsApiResponse response, String message);
    void didError(String message);
}
