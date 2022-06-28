package com.foodapp.eatme.model.extend;

public class Bad {
    private String name;
    private String amount;
    private boolean indented;
    private double percentOfDailyNeeds;

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
