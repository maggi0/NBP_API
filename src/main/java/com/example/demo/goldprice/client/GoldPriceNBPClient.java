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
    public Document fetchGoldPrices() throws IOException, ParserConfigurationException, SAXException {
        URL url = new URL("http://api.nbp.pl/api/cenyzlota/last/14/?format=xml");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();

        return db.parse(conn.getInputStream());
    }
}
