package com.foodapp.eatme.api;


import com.foodapp.eatme.model.Step;

public interface StepsListener {
    void didFetch(Step response, String message);

    void didError(String message);
}
