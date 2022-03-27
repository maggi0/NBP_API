package com.example.demo;

import com.example.demo.goldprice.service.IGoldPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DemoApplication {

	@Autowired
	private IGoldPriceService goldPriceService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
