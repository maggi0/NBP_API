package com.example.demo.exchangerates.service;

import com.example.demo.exchangerates.model.ExchangeRates;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
class ExchangeRatesService implements IExchangeRatesService{

    public List<ExchangeRates> getExchangeRates(@PathVariable String currencyCode) throws ParserConfigurationException, IOException, SAXException {

        List<ExchangeRates> list = new ArrayList<>();

        Document doc = connectHTTP(currencyCode);
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

    public Document connectHTTP(String currencyCode) throws ParserConfigurationException, IOException, SAXException {

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
