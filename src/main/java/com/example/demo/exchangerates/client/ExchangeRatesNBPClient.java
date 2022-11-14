package com.example.demo.exchangerates.client;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
class ExchangeRatesNBPClient implements IExchangeRatesNBPClient{

    @Override
    public Document fetchExchangeRates(String currencyCode) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();

        final var urlA = new URL("http://api.nbp.pl/api/exchangerates/rates/a/" + currencyCode + "/last/20/?format=xml");

        try (final var inputStream = fetchRate(urlA)) {
            return db.parse(inputStream);
        } catch (Exception e) {
            final var urlB = new URL("http://api.nbp.pl/api/exchangerates/rates/b/" + currencyCode + "/last/20/?format=xml");
            final var inputStream = fetchRate(urlB);
            return db.parse(inputStream);
        }
    }

    private InputStream fetchRate(final URL url) throws IOException {
        final var connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        if(connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            connection.disconnect();
            throw new ConnectException();
        }

        return connection.getInputStream();
    }
}
