package com.foodapp.eatme.model;

import java.util.ArrayList;

public class Nutrition {
    private ArrayList<Nutrient> nutrients;

    public ArrayList<Nutrient> getNutrients() {
        return nutrients;
    }

    public void setNutrients(ArrayList<Nutrient> nutrients) {
        this.nutrients = nutrients;
    }

    public Nutrition() {
    }

    public Nutrition(ArrayList<Nutrient> nutrients) {
        this.nutrients = nutrients;
    }
}
