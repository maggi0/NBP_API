package com.example.demo.exchangerates.service;

import com.example.demo.exception.CurrencyNotFoundException;
import com.example.demo.exchangerates.model.ExchangeRates;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public interface IExchangeRatesService {

    List<ExchangeRates> getExchangeRates(String currencyCode) throws ParserConfigurationException, IOException, SAXException, CurrencyNotFoundException;

}
