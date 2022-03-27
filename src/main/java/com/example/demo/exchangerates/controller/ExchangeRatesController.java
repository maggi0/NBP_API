package com.example.demo.exchangerates.controller;

import com.example.demo.exception.CurrencyNotFoundException;
import com.example.demo.exchangerates.model.ExchangeRates;
import com.example.demo.exchangerates.service.IExchangeRatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

@Controller
public class ExchangeRatesController {

    private final IExchangeRatesService exchangeRatesService;

    @Autowired
    public ExchangeRatesController(IExchangeRatesService exchangeRatesService)
    {
        this.exchangeRatesService = exchangeRatesService;
    }

    @GetMapping("/api/exchange-rates/{currencyCode}")
    public String getExchangeRates(@PathVariable String currencyCode, Model model) throws ParserConfigurationException, IOException, CurrencyNotFoundException, SAXException {

        Currency code;
        try {
            code = Currency.getInstance(currencyCode.toUpperCase());
        }
        catch (IllegalArgumentException e) {
            throw new CurrencyNotFoundException("Currency corresponding to code " + currencyCode + " not found!");
        }

        List<ExchangeRates> list = exchangeRatesService.getExchangeRates(currencyCode);
        String currencyName = code.getDisplayName(Locale.US);

        model.addAttribute("data", list);
        model.addAttribute("currencyCode", currencyCode.toUpperCase());
        model.addAttribute("currencyName", currencyName);

        return "exchangerates";
    }

}
