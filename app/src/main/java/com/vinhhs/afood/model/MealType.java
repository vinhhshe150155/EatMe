package com.vinhhs.afood.model;


public class MealType {
    private String name;
    private int image;
    private boolean selected;

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

    public MealType(String name, int image) {
        this.name = name;
        this.image = image;
    }
}
