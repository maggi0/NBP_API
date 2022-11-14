package com.example.demo.exchangerates.service;

import com.example.demo.exception.CurrencyNotFoundException;
import com.example.demo.exchangerates.client.IExchangeRatesNBPClient;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExchangeRatesServiceUnitTest {

    @InjectMocks
    IExchangeRatesNBPClient exchangeRatesNBPClient;

    @Test
    void shouldThrowExceptionWhenCurrencyCodeNotFound() {
        String wrongCurrencyCode = "wrongCode";

        ExchangeRatesService exchangeRatesService = new ExchangeRatesService(exchangeRatesNBPClient);
        CurrencyNotFoundException thrown = assertThrows(CurrencyNotFoundException.class, () -> exchangeRatesService.getExchangeRates(wrongCurrencyCode), "CurrencyNotFoundException was expected");

        assertEquals("Currency corresponding to code " + wrongCurrencyCode + " not found!",
                thrown.getMessage());
    }



}
