package com.example.demo.unit;

import com.example.demo.exception.CurrencyNotFoundException;
import com.example.demo.exchangerates.controller.ExchangeRatesController;
import com.example.demo.exchangerates.service.IExchangeRatesService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExchangeRatesControllerUnitTest {

    @InjectMocks
    IExchangeRatesService exchangeRatesService;

    @Test
    void shouldThrowExceptionWhenCurrencyCodeNotFound() throws Exception {
        String wrongCurrencyCode = "wrongCode";

        ExchangeRatesController exchangeRatesController = new ExchangeRatesController(exchangeRatesService);

        CurrencyNotFoundException thrown = assertThrows(CurrencyNotFoundException.class, () -> exchangeRatesController.getExchangeRates(wrongCurrencyCode, null), "CurrencyNotFoundException was expected");

        assertEquals("Currency corresponding to code " + wrongCurrencyCode + " not found!",
                thrown.getMessage());
    }

}
