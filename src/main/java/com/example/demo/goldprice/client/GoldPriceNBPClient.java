package com.example.demo.goldprice.client;

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
class GoldPriceNBPClient implements IGoldPriceNBPClient{

    @Override
    public Document fetchGoldPrices() throws IOException, ParserConfigurationException, SAXException {
        final var url = new URL("http://api.nbp.pl/api/cenyzlota/last/20/?format=xml");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();

        return db.parse(connection.getInputStream());
    }
}
