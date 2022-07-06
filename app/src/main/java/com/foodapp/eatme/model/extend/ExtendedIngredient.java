package com.foodapp.eatme.model.extend;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

import java.util.List;

public class ExtendedIngredient implements Parcelable {
    @Expose
    private int id;
    @Expose
    private String aisle;
    @Expose
    private String image;
    @Expose
    private String consistency;
    @Expose
    private String name;
    @Expose
    private String nameClean;
    @Expose
    private String original;
    @Expose
    private String originalName;
    @Expose
    private double amount;

    public ExtendedIngredient() {
    }

    protected ExtendedIngredient(Parcel in) {
        id = in.readInt();
        aisle = in.readString();
        image = in.readString();
        consistency = in.readString();
        name = in.readString();
        nameClean = in.readString();
        original = in.readString();
        originalName = in.readString();
        amount = in.readDouble();
        unit = in.readString();
        meta = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(aisle);
        dest.writeString(image);
        dest.writeString(consistency);
        dest.writeString(name);
        dest.writeString(nameClean);
        dest.writeString(original);
        dest.writeString(originalName);
        dest.writeDouble(amount);
        dest.writeString(unit);
        dest.writeStringList(meta);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ExtendedIngredient> CREATOR = new Creator<ExtendedIngredient>() {
        @Override
        public ExtendedIngredient createFromParcel(Parcel in) {
            return new ExtendedIngredient(in);
        }

        @Override
        public ExtendedIngredient[] newArray(int size) {
            return new ExtendedIngredient[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAisle() {
        return aisle;
    }

    public void setAisle(String aisle) {
        this.aisle = aisle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getConsistency() {
        return consistency;
    }

    public void setConsistency(String consistency) {
        this.consistency = consistency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameClean() {
        return nameClean;
    }

    public void setNameClean(String nameClean) {
        this.nameClean = nameClean;
    }

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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public List<String> getMeta() {
        return meta;
    }

    public void setMeta(List<String> meta) {
        this.meta = meta;
    }

    public Measures getMeasures() {
        return measures;
    }

    public void setMeasures(Measures measures) {
        this.measures = measures;
    }

    public ExtendedIngredient(int id, String aisle, String image, String consistency, String name, String nameClean, String original, String originalName, double amount, String unit, List<String> meta, Measures measures) {
        this.id = id;
        this.aisle = aisle;
        this.image = image;
        this.consistency = consistency;
        this.name = name;
        this.nameClean = nameClean;
        this.original = original;
        this.originalName = originalName;
        this.amount = amount;
        this.unit = unit;
        this.meta = meta;
        this.measures = measures;
    }

    private String unit;
    private List<String> meta;
    private Measures measures;
}
