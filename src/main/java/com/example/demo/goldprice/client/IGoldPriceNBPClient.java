package com.example.demo.goldprice.client;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public interface IGoldPriceNBPClient {
    Document fetchGoldPrices() throws IOException, ParserConfigurationException, SAXException;

}
