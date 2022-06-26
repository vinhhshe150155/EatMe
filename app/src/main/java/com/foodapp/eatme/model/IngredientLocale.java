package com.foodapp.eatme.model;

public class IngredientLocale {
    private int id;

    public IngredientLocale() {
    }

    public IngredientLocale(int id, String enName, String krName) {
        this.id = id;
        this.enName = enName;
        this.krName = krName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getKrName() {
        return krName;
    }

    public void setKrName(String krName) {
        this.krName = krName;
    }

    private String enName;
    private String krName;
}
