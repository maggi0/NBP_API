package com.example.demo.goldprice;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class GoldPriceService {

    public List<GoldPrice> getGoldPrice() throws IOException, ParserConfigurationException, SAXException {

        List<GoldPrice> list = new ArrayList<>();

        Document doc = connectHTTP();
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

    private Document connectHTTP() throws IOException, ParserConfigurationException, SAXException {
        URL url = new URL("http://api.nbp.pl/api/cenyzlota/last/14/?format=xml");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();

        return db.parse(conn.getInputStream());
    }
}
