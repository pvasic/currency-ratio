package com.pvasic.currencyratio.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "GiphyClient", url = "${giphy.url}")
public interface FeignGiphyClient {
    @GetMapping
    ResponseEntity<Map> getGifByPhrase(
            @RequestParam("api_key") String apiKey,
            @RequestParam("q") String phrase,
            @RequestParam("limit") int number);
}
