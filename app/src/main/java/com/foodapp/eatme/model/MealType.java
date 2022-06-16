package com.foodapp.eatme.model;


public class MealType {
    private String name;
    private int image;
    private boolean selected;
    private int strResource;
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public MealType() {
    }

    public int getStrResource() {
        return strResource;
    }

    public void setStrResource(int strResource) {
        this.strResource = strResource;
    }

    public MealType(String name, int image) {
        this.name = name;
        this.image = image;
    }
    public MealType(String name, int image, int strResource) {
        this.name = name;
        this.image = image;
        this.strResource = strResource;
    }
}
