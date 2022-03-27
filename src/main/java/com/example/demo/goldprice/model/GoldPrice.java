package com.example.demo.goldprice.model;

import java.time.LocalDate;

public class GoldPrice {
    private final LocalDate date;
    private final Double price;

    public GoldPrice(LocalDate date, Double price) {
        this.date = date;
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "GoldPrice{" +
                "date=" + date +
                ", price=" + price +
                '}';
    }
}
