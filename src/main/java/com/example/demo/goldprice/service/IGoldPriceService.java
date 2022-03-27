package com.example.demo.goldprice.service;

import com.example.demo.goldprice.model.GoldPrice;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public interface IGoldPriceService {

    List<GoldPrice> getGoldPrice() throws IOException, ParserConfigurationException, SAXException;

}
