package com.foodapp.eatme.model;

public class Diet {
    private String name;
    private int image;
    private boolean selected;
    private int strResource;

    public boolean isSelected() {
        return selected;
    }

    public Diet(String name, int image, int strResource, boolean selected) {
        this.name = name;
        this.image = image;
        this.selected = selected;
        this.strResource = strResource;
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

    public Diet() {
    }

    public int getStrResource() {
        return strResource;
    }

    public void setStrResource(int strResource) {
        this.strResource = strResource;
    }

    public Diet(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public Diet(String name, int image, int strResource) {
        this.name = name;
        this.image = image;
        this.strResource = strResource;
    }
}
