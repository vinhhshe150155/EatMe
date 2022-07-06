package com.foodapp.eatme.model.extend;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

public class Good implements Parcelable {
    @Expose
    private String amount;
    @Expose
    private boolean indented;
    @Expose
    private double percentOfDailyNeeds;
    @Expose
    private String name;

    protected Good(Parcel in) {
        amount = in.readString();
        indented = in.readByte() != 0;
        percentOfDailyNeeds = in.readDouble();
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(amount);
        dest.writeByte((byte) (indented ? 1 : 0));
        dest.writeDouble(percentOfDailyNeeds);
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Good> CREATOR = new Creator<Good>() {
        @Override
        public Good createFromParcel(Parcel in) {
            return new Good(in);
        }

        @Override
        public Good[] newArray(int size) {
            return new Good[size];
        }
    };

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Good() {
    }

    public Good(String amount, boolean indented, double percentOfDailyNeeds, String name) {
        this.amount = amount;
        this.indented = indented;
        this.percentOfDailyNeeds = percentOfDailyNeeds;
        this.name = name;
    }
}
