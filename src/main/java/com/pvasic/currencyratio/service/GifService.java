package com.pvasic.currencyratio.service;

import com.pvasic.currencyratio.feignclient.FeignGiphyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GifService {

    private final int NUMBER_GIF = 1;

    private final FeignGiphyClient giphyClient;

    @Value("${giphy.api-key}")
    private String apiKey;

    @Value("${giphy.rich}")
    private String rich;

    @Value("${giphy.broke}")
    private String broke;

    @Autowired
    public GifService(FeignGiphyClient giphyClient) {
        this.giphyClient = giphyClient;
    }

    public ResponseEntity<Map> getGif(boolean isRise) {
        if (isRise) {
            return giphyClient.getGifByPhrase(apiKey, rich, NUMBER_GIF);
        } else {
            return giphyClient.getGifByPhrase(apiKey, broke, NUMBER_GIF);
        }
    }

}
