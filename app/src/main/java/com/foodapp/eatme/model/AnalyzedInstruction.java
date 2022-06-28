package com.foodapp.eatme.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

import java.util.List;

public class AnalyzedInstruction implements Parcelable {
    @Expose
    private String name;
    @Expose
    private List<Step> steps;

    public AnalyzedInstruction(String name, List<Step> steps) {
        this.name = name;
        this.steps = steps;
    }

    public AnalyzedInstruction() {
    }


    protected AnalyzedInstruction(Parcel in) {
        name = in.readString();
        steps = in.createTypedArrayList(Step.CREATOR);
    }

    public static final Creator<AnalyzedInstruction> CREATOR = new Creator<AnalyzedInstruction>() {
        @Override
        public AnalyzedInstruction createFromParcel(Parcel in) {
            return new AnalyzedInstruction(in);
        }

        @Override
        public AnalyzedInstruction[] newArray(int size) {
            return new AnalyzedInstruction[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(name);
        parcel.writeTypedList(steps);
    }


}
