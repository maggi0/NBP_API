package com.example.demo.goldprice;

import java.time.LocalDate;

public class GoldPrice {
    private LocalDate date;
    private Double price;

    public GoldPrice(LocalDate date, Double price) {
        this.date = date;
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "GoldPrice{" +
                "date=" + date +
                ", price=" + price +
                '}';
    }
}
