package com.example.demo.exchangerates.model;

import java.time.LocalDate;

public class ExchangeRates {
    private final LocalDate date;
    private final Double exchangeRate;

    public ExchangeRates(LocalDate date, Double exchangeRate) {
        this.date = date;
        this.exchangeRate = exchangeRate;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getExchangeRate() {
        return exchangeRate;
    }

    @Override
    public String toString() {
        return "ExchangeRates{" +
                "date=" + date +
                ", exchangeRate=" + exchangeRate +
                '}';
    }
}
