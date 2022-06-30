package com.foodapp.eatme.model.extend;

public class Good {
    private String amount;
    private boolean indented;
    private double percentOfDailyNeeds;
    private String name;

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
