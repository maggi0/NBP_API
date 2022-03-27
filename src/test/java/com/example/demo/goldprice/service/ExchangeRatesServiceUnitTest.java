package com.example.demo.goldprice.service;

import com.example.demo.exception.CurrencyNotFoundException;
import com.example.demo.exchangerates.service.ExchangeRatesService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExchangeRatesServiceUnitTest {


    @Test
    void shouldThrowExceptionWhenCurrencyCodeNotFound() throws Exception {
        String wrongCurrencyCode = "wrongCode";

        ExchangeRatesService exchangeRatesService;
        CurrencyNotFoundException thrown = assertThrows(CurrencyNotFoundException.class, () -> exchangeRatesService.getExchangeRates(wrongCurrencyCode), "CurrencyNotFoundException was expected");

        assertEquals("Currency corresponding to code " + wrongCurrencyCode + " not found!",
                thrown.getMessage());
    }

}
