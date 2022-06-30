package com.foodapp.eatme.model.extend;

public class Measures {
    private Us us;
    private Metric metric;

    public Us getUs() {
        return us;
    }

    public Measures() {
    }

    public void setUs(Us us) {
        this.us = us;
    }

    public Metric getMetric() {
        return metric;
    }

    public void setMetric(Metric metric) {
        this.metric = metric;
    }

    public Measures(Us us, Metric metric) {
        this.us = us;
        this.metric = metric;
    }
}
