package com.example.demo.goldprice.service;

import com.example.demo.goldprice.client.IGoldPriceNBPClient;
import com.example.demo.goldprice.model.GoldPrice;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
class GoldPriceService implements IGoldPriceService{

    private final IGoldPriceNBPClient goldPriceNBPClient;

    GoldPriceService(IGoldPriceNBPClient goldPriceNBPClient) {
        this.goldPriceNBPClient = goldPriceNBPClient;
    }

    @Override
    public List<GoldPrice> getGoldPrice() throws IOException, ParserConfigurationException, SAXException {

        List<GoldPrice> list = new ArrayList<>();

        Document doc = goldPriceNBPClient.fetchGoldPrices();
        NodeList goldPriceNodes = doc.getElementsByTagName("CenaZlota");

        for (int i = 0; i < goldPriceNodes.getLength(); i++) {
            Node nNode = goldPriceNodes.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                LocalDate date = LocalDate.parse(eElement.getElementsByTagName("Data").item(0).getTextContent());
                Double price = Double.parseDouble(eElement.getElementsByTagName("Cena").item(0).getTextContent());
                list.add(new GoldPrice(date, price));
            }
        }
        return list;
    }
}
