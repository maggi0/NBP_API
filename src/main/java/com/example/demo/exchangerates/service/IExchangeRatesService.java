package com.example.demo.exchangerates.service;

import com.example.demo.exchangerates.model.ExchangeRates;
import org.springframework.web.bind.annotation.PathVariable;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public interface IExchangeRatesService {

    List<ExchangeRates> getExchangeRates(@PathVariable String currencyCode) throws ParserConfigurationException, IOException, SAXException;
    Document connectHTTP(String currencyCode) throws ParserConfigurationException, IOException, SAXException;

}
