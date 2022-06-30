package com.foodapp.eatme.model;

public class Length {
    public int number;
    public String unit;

    public Length(int number, String unit) {
        this.number = number;
        this.unit = unit;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getUnit() {
        return unit;
    }

    public Length() {
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
