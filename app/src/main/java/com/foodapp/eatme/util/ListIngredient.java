package com.foodapp.eatme.util;

import android.content.Context;

import com.foodapp.eatme.model.IngredientLocale;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListIngredient {
    private Context context;
    private List<IngredientLocale> ingredients = null;
    private static ListIngredient instance = null;

    public ListIngredient() {

    }

    public static ListIngredient getInstance() {
        if (instance == null)
            instance = new ListIngredient();
        return instance;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<IngredientLocale> getAllIngredient() {
        BufferedReader reader;
        try {
            ingredients = new ArrayList<>();
            final InputStream file = context.getAssets().open("list_ingredients.txt");
            reader = new BufferedReader(new InputStreamReader(file));
            String line = reader.readLine();
            while (line != null) {
                String name;
                String[] ingredientArr = line.split(";");
                IngredientLocale ingredient = new IngredientLocale();
                ingredient.setKrName(ingredientArr[1]);
                ingredient.setEnName(ingredientArr[2]);
                ingredient.setId(Integer.parseInt(ingredientArr[0]));
                ingredients.add(ingredient);
                line = reader.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return ingredients;
    }


    public List<IngredientLocale> getRandomMainIngredients() {
        if (ingredients == null) {
            ingredients = getAllIngredient();
        }
        List<IngredientLocale> randomIngredients = null;
        try {
            randomIngredients = new ArrayList<>();
            while (randomIngredients.size() < 1) {
                Random rand = new Random();
                int i = rand.nextInt(ingredients.size());
                IngredientLocale randomElement = ingredients.get(i);
                if (!randomIngredients.contains(randomElement)) {
                    randomIngredients.add(randomElement);
                }
            }
        } catch (Exception e) {
        }
        return randomIngredients;
    }
}
