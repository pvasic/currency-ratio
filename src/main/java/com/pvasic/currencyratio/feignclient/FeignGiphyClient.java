package com.pvasic.currencyratio.feignclient;

import com.pvasic.currencyratio.model.gif.Gif;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "GiphyClient", url = "${giphy.url}")
public interface FeignGiphyClient {
    @GetMapping
    Gif getGifByPhrase(
            @RequestParam("api_key") String apiKey,
            @RequestParam("q") String phrase,
            @RequestParam("limit") int number);
}
