package com.foodapp.eatme.api;


import com.foodapp.eatme.model.extend.NutriExtend;

public interface NutriListener {
    void didFetch(NutriExtend nutriExtend, String message);

    void didError(String message);
}
