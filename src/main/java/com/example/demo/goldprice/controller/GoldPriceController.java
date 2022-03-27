package com.example.demo.goldprice.controller;

import com.example.demo.goldprice.model.GoldPrice;
import com.example.demo.goldprice.service.IGoldPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

@Controller
public class GoldPriceController {

    private final IGoldPriceService goldPriceService;

    @Autowired
    public GoldPriceController(IGoldPriceService goldPriceService)
    {
        this.goldPriceService = goldPriceService;
    }

    @GetMapping("/api/gold-price/average")
    public String getGoldPrice(Model model) throws IOException, ParserConfigurationException, SAXException {
        List<GoldPrice> list = goldPriceService.getGoldPrice();
        model.addAttribute("data", list);

        return "goldprices";
    }
}
