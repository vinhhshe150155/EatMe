package com.foodapp.eatme.model.extend;

import java.util.List;

public class NutriExtend {
    private String calories;
    private String carbs;
    private String fat;
    private String protein;
    private List<Bad> bad;
    private List<Good> good;

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getCarbs() {
        return carbs;
    }

    public void setCarbs(String carbs) {
        this.carbs = carbs;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public List<Bad> getBad() {
        return bad;
    }

    public void setBad(List<Bad> bad) {
        this.bad = bad;
    }

    public List<Good> getGood() {
        return good;
    }

    public void setGood(List<Good> good) {
        this.good = good;
    }

    public NutriExtend() {
    }

    public NutriExtend(String calories, String carbs, String fat, String protein, List<Bad> bad, List<Good> good) {
        this.calories = calories;
        this.carbs = carbs;
        this.fat = fat;
        this.protein = protein;
        this.bad = bad;
        this.good = good;
    }
}
