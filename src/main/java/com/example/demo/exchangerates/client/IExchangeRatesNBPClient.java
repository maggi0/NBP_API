package com.example.demo.exchangerates.client;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public interface IExchangeRatesNBPClient {
    Document fetchExchangeRates(String currencyCode) throws ParserConfigurationException, IOException, SAXException;

}
