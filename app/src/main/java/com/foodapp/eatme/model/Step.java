package com.foodapp.eatme.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Step implements Parcelable {
    private int number;
    private String step;
    private List<Ingredient> ingredients;
    private List<Equipment> equipment;
    private Length length;

    public Step(int number, String step, List<Ingredient> ingredients, List<Equipment> equipment, Length length) {
        this.number = number;
        this.step = step;
        this.ingredients = ingredients;
        this.equipment = equipment;
        this.length = length;
    }

    public Step() {
    }

    protected Step(Parcel in) {
        number = in.readInt();
        step = in.readString();
    }

    public static final Creator<Step> CREATOR = new Creator<Step>() {
        @Override
        public Step createFromParcel(Parcel in) {
            return new Step(in);
        }

        @Override
        public Step[] newArray(int size) {
            return new Step[size];
        }
    };

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Equipment> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<Equipment> equipment) {
        this.equipment = equipment;
    }

    public Length getLength() {
        return length;
    }

    public void setLength(Length length) {
        this.length = length;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(number);
        parcel.writeString(step);
    }
}
