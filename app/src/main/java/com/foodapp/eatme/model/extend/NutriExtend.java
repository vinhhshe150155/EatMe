package com.foodapp.eatme.model.extend;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

import java.util.List;

public class NutriExtend implements Parcelable {
    @Expose
    private String calories;
    @Expose
    private String carbs;
    @Expose
    private String fat;
    @Expose
    private String protein;
    @Expose
    private List<Bad> bad;
    @Expose
    private List<Good> good;

    protected NutriExtend(Parcel in) {
        calories = in.readString();
        carbs = in.readString();
        fat = in.readString();
        protein = in.readString();
    }

    public static final Creator<NutriExtend> CREATOR = new Creator<NutriExtend>() {
        @Override
        public NutriExtend createFromParcel(Parcel in) {
            return new NutriExtend(in);
        }

        @Override
        public NutriExtend[] newArray(int size) {
            return new NutriExtend[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(calories);
        parcel.writeString(carbs);
        parcel.writeString(fat);
        parcel.writeString(protein);
    }
}
