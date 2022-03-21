package com.example.demo.exchangerates;

import java.time.LocalDate;

public class ExchangeRates {
    private LocalDate date;
    private Double exchangeRate;

    public ExchangeRates(LocalDate date, Double exchangeRate) {
        this.date = date;
        this.exchangeRate = exchangeRate;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(Double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    @Override
    public String toString() {
        return "ExchangeRates{" +
                "date=" + date +
                ", exchangeRate=" + exchangeRate +
                '}';
    }
}
