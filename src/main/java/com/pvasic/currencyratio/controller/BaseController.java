package com.pvasic.currencyratio.controller;

import com.pvasic.currencyratio.model.ExchangeRate;
import com.pvasic.currencyratio.service.RatesService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final RatesService ratesService;

    @Autowired
    public BaseController(RatesService ratesService) {
        this.ratesService = ratesService;
    }

    @GetMapping("/get-giph-by-code/{code}")
    ResponseEntity<ExchangeRate> getGiphByCode(@PathVariable String code) {
        boolean rise = ratesService.isRateRise(code);
//        Dummy
        return ResponseEntity.of(Optional.empty());
    }
}
