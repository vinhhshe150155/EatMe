package com.example.demorandomrecipe.listeners;

import com.example.demorandomrecipe.model.RandomRecipeAPIRes;

public interface RandomRecipeRespondListener {
    void didFetch(RandomRecipeAPIRes res, String message);
    void didError(String message);
}
