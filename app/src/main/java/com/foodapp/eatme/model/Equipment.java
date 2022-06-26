package com.foodapp.eatme.model;

public class Equipment {
    private int id;
    private String name;
    private String localizedName;
    private String image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Equipment() {
    }

    public Equipment(int id, String name, String localizedName, String image) {
        this.id = id;
        this.name = name;
        this.localizedName = localizedName;
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocalizedName() {
        return localizedName;
    }

    public void setLocalizedName(String localizedName) {
        this.localizedName = localizedName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
