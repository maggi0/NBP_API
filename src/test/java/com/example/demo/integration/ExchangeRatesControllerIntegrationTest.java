package com.example.demo.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.apache.commons.lang3.StringUtils.countMatches;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class ExchangeRatesControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void validInputTestStatusAndLength() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/api/exchange-rates/CHF");
        MvcResult result = mvc.perform(request).andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(200, status);

        String content = result.getResponse().getContentAsString();
        String value = "<td>";
        int count = countMatches(content, value);
        int expectedListLength = 5;
        assertEquals(expectedListLength, count/2);
    }

    @Test
    void invalidInputTestStatus() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/wrong/endpoint");
        MvcResult result = mvc.perform(request).andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(404, status);
    }
}
