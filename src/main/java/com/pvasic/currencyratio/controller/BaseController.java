package com.pvasic.currencyratio.controller;

import com.pvasic.currencyratio.feignclient.FeignRatesClient;
import com.pvasic.currencyratio.model.ExchangeRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = BaseController.URL)
public class BaseController {
    static final String URL = "/api";

    private final FeignRatesClient ratesClient;

    @Value("${openexchangerates.your-id}")
    private String yourId;

    @Autowired
    public BaseController(FeignRatesClient ratesClient) {
        this.ratesClient = ratesClient;
    }

    @GetMapping("/get-giph-by-code/{code}")
    ResponseEntity<ExchangeRate> getGiphByCode(@PathVariable String code) {

//        Dummy
        return ResponseEntity.of(Optional.of(ratesClient.getLatestRate(yourId)));
    }
}
