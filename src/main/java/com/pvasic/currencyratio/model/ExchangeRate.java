package com.pvasic.currencyratio.model;

import java.util.Map;

public class ExchangeRate {
    private Map<String, Double> rates;

    public ExchangeRate() {
    }

    public ExchangeRate(Map<String, Double> rates) {
        this.rates = rates;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }
}
