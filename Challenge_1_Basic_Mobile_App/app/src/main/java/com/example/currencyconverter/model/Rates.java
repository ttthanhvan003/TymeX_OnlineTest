package com.example.currencyconverter.model;

import java.util.Map;

public class Rates {
    private Map<String, Double> rates;

    public Rates(Map<String, Double> rates) {
        this.rates = rates;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }
}
