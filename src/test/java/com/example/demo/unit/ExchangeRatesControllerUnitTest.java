package com.example.demo.unit;

import com.example.demo.exception.CurrencyNotFoundException;
import com.example.demo.exchangerates.ExchangeRatesController;
import com.example.demo.exchangerates.ExchangeRatesService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExchangeRatesControllerUnitTest {

    @Test
    void shouldThrowExceptionWhenCurrencyCodeNotFound() throws Exception {
        String wrongCurrencyCode = "wrongCode";

        ExchangeRatesService exchangeRatesService = Mockito.mock(ExchangeRatesService.class);
        ExchangeRatesController exchangeRatesController = new ExchangeRatesController(exchangeRatesService);

        CurrencyNotFoundException thrown = assertThrows(CurrencyNotFoundException.class, () -> exchangeRatesController.getExchangeRates(wrongCurrencyCode, null), "CurrencyNotFoundException was expected");

        assertEquals("Currency corresponding to code " + wrongCurrencyCode + " not found!",
                thrown.getMessage());
    }

}
