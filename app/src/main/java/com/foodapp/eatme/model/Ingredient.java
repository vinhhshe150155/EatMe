package com.foodapp.eatme.model;

import java.util.List;

public class Ingredient {
    private int id;
    private String name;
    private String localizedName;
    private String image;
    private String original;
    private String originalName;
    private List<String> possibleUnits;
    private String consistency;
    private List<String> shoppingListUnits;

    public Ingredient() {
    }

    public Ingredient(int id, String name, String localizedName, String image, String original, String originalName, List<String> possibleUnits, String consistency, List<String> shoppingListUnits, List<String> meta, List<String> categoryPath, String aisle) {
        this.id = id;
        this.name = name;
        this.localizedName = localizedName;
        this.image = image;
        this.original = original;
        this.originalName = originalName;
        this.possibleUnits = possibleUnits;
        this.consistency = consistency;
        this.shoppingListUnits = shoppingListUnits;
        this.meta = meta;
        this.categoryPath = categoryPath;
        this.aisle = aisle;
    }

    private List<String> meta;
    private List<String> categoryPath;
    private String aisle;

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public List<String> getPossibleUnits() {
        return possibleUnits;
    }

    public void setPossibleUnits(List<String> possibleUnits) {
        this.possibleUnits = possibleUnits;
    }

    public String getConsistency() {
        return consistency;
    }

    public void setConsistency(String consistency) {
        this.consistency = consistency;
    }

    public List<String> getShoppingListUnits() {
        return shoppingListUnits;
    }

    public void setShoppingListUnits(List<String> shoppingListUnits) {
        this.shoppingListUnits = shoppingListUnits;
    }

    public List<String> getMeta() {
        return meta;
    }

    public void setMeta(List<String> meta) {
        this.meta = meta;
    }

    public List<String> getCategoryPath() {
        return categoryPath;
    }

    public void setCategoryPath(List<String> categoryPath) {
        this.categoryPath = categoryPath;
    }

    public String getAisle() {
        return aisle;
    }

    public void setAisle(String aisle) {
        this.aisle = aisle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
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
