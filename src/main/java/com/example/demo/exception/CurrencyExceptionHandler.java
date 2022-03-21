package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CurrencyExceptionHandler {

    @ExceptionHandler(value = {CurrencyNotFoundException.class})
    public String handleCurrencyException(CurrencyNotFoundException e, Model model) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        CurrencyException currencyException = new CurrencyException(e.getMessage(), badRequest);
        model.addAttribute("exception", currencyException);

        return "exception";
    }
}
