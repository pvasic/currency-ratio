package com.pvasic.currencyratio.controller;

import com.pvasic.currencyratio.service.GifService;
import com.pvasic.currencyratio.service.RatesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = BaseController.URL)
public class BaseController {
    static final String URL = "/api";

    private final RatesService ratesService;
    private final GifService gifService;

    @GetMapping("/codes")
    List<String> getCodes() {
        return ratesService.getCodes();
    }

    @GetMapping("/gifs/{code}")
    String getGifByCode(@PathVariable String code) {
        boolean isRise = ratesService.isRateRise(code);
        return gifService.getGifUrl(isRise);
    }
}
