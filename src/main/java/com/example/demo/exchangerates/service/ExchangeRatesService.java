package com.example.demo.exchangerates.service;

import com.example.demo.exception.CurrencyNotFoundException;
import com.example.demo.exchangerates.client.IExchangeRatesNBPClient;
import com.example.demo.exchangerates.model.ExchangeRates;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

@Service
class ExchangeRatesService implements IExchangeRatesService{

    private final IExchangeRatesNBPClient exchangeRatesNBPClient;

    ExchangeRatesService(IExchangeRatesNBPClient exchangeRatesNBPClient) {
        this.exchangeRatesNBPClient = exchangeRatesNBPClient;
    }

    @Override
    public List<ExchangeRates> getExchangeRates(String currencyCode) throws ParserConfigurationException, IOException, SAXException, CurrencyNotFoundException {

        List<ExchangeRates> list = new ArrayList<>();

        checkForException(currencyCode);

        Document doc = exchangeRatesNBPClient.fetchExchangeRates(currencyCode);
        NodeList exchangeRatesNodes = doc.getElementsByTagName("Rate");

        for (int i = 0; i < exchangeRatesNodes.getLength(); i++) {
            Node nNode = exchangeRatesNodes.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                LocalDate date = LocalDate.parse(eElement.getElementsByTagName("EffectiveDate").item(0).getTextContent());
                double price = Double.parseDouble(eElement.getElementsByTagName("Mid").item(0).getTextContent());
                BigDecimal exRate = new BigDecimal(1/price).setScale(5, RoundingMode.HALF_UP);
                list.add(new ExchangeRates(date, exRate.doubleValue()));
            }
        }
        return list;
    }

    void checkForException(String currencyCode) throws CurrencyNotFoundException {
        try {
            Currency code = Currency.getInstance(currencyCode.toUpperCase());
        }
        catch (IllegalArgumentException e) {
            throw new CurrencyNotFoundException("Currency corresponding to code " + currencyCode + " not found!");
        }
    }
}
