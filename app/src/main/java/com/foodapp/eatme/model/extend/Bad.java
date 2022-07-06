package com.foodapp.eatme.model.extend;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

public class Bad implements Parcelable {
    @Expose
    private String name;
    @Expose
    private String amount;
    @Expose
    private boolean indented;
    @Expose
    private double percentOfDailyNeeds;

    protected Bad(Parcel in) {
        name = in.readString();
        amount = in.readString();
        indented = in.readByte() != 0;
        percentOfDailyNeeds = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(amount);
        dest.writeByte((byte) (indented ? 1 : 0));
        dest.writeDouble(percentOfDailyNeeds);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Bad> CREATOR = new Creator<Bad>() {
        @Override
        public Bad createFromParcel(Parcel in) {
            return new Bad(in);
        }

        @Override
        public Bad[] newArray(int size) {
            return new Bad[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public boolean isIndented() {
        return indented;
    }

    public void setIndented(boolean indented) {
        this.indented = indented;
    }

    public double getPercentOfDailyNeeds() {
        return percentOfDailyNeeds;
    }

    public void setPercentOfDailyNeeds(double percentOfDailyNeeds) {
        this.percentOfDailyNeeds = percentOfDailyNeeds;
    }

    public Bad() {
    }

    public Bad(String name, String amount, boolean indented, double percentOfDailyNeeds) {
        this.name = name;
        this.amount = amount;
        this.indented = indented;
        this.percentOfDailyNeeds = percentOfDailyNeeds;
    }
}
