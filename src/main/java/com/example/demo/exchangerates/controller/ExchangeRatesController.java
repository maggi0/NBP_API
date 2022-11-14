package com.example.demo.exchangerates.controller;

import com.example.demo.exception.CurrencyNotFoundException;
import com.example.demo.exchangerates.model.ExchangeRates;
import com.example.demo.exchangerates.service.IExchangeRatesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

@Controller
public class ExchangeRatesController {

    private final IExchangeRatesService exchangeRatesService;

    public ExchangeRatesController(IExchangeRatesService exchangeRatesService) {
        this.exchangeRatesService = exchangeRatesService;
    }

    @GetMapping("/api/exchange-rates/{currencyCode}")
    public String getExchangeRates(@PathVariable String currencyCode, Model model) throws ParserConfigurationException, IOException, SAXException, CurrencyNotFoundException {

        List<ExchangeRates> list = exchangeRatesService.getExchangeRates(currencyCode);

        Currency code = Currency.getInstance(currencyCode.toUpperCase());
        String currencyName = code.getDisplayName(Locale.US);

        model.addAttribute("data", list);
        model.addAttribute("currencyCode", currencyCode.toUpperCase());
        model.addAttribute("currencyName", currencyName);

        return "exchangerates";
    }

}
