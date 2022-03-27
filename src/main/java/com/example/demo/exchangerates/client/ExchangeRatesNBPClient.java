package com.example.demo.exchangerates.client;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
class ExchangeRatesNBPClient implements IExchangeRatesNBPClient{

    public Document fetchExchangeRates(String currencyCode) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();

        URL urlA = new URL("https://api.nbp.pl/api/exchangerates/rates/a/" + currencyCode + "/last/5/?format=xml");
        HttpURLConnection connA = (HttpURLConnection) urlA.openConnection();
        connA.setRequestMethod("GET");
        connA.connect();

        int resA = connA.getResponseCode();
        if(resA != 200) {
            URL urlB = new URL("https://api.nbp.pl/api/exchangerates/rates/b/" + currencyCode + "/last/5/?format=xml");
            HttpURLConnection connB = (HttpURLConnection) urlB.openConnection();
            connB.setRequestMethod("GET");
            connB.connect();
            return db.parse(connB.getInputStream());
        }
        else
            return db.parse(connA.getInputStream());
    }
}
