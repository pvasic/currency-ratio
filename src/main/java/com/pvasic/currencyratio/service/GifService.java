package com.pvasic.currencyratio.service;

import com.pvasic.currencyratio.feignclient.FeignGiphyClient;
import com.pvasic.currencyratio.model.gif.Gif;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class GifService {
    public static final int NUMBER_GIF = 10;
    private final Random RANDOM = new Random();

    private final FeignGiphyClient giphyClient;

    @Value("${giphy.api-key}")
    private String apiKey;

    @Value("${giphy.rich}")
    private String rich;

    @Value("${giphy.broke}")
    private String broke;

    public Gif getGif(boolean isRise) {
        return isRise
                ? giphyClient.getGifByPhrase(apiKey, rich, NUMBER_GIF)
                : giphyClient.getGifByPhrase(apiKey, broke, NUMBER_GIF);
    }

    public String getGifUrl(boolean isRise) {
        return getGif(isRise).getData()[genRandomPosition()].getImages().getOriginal().getUrl();
    }

    private int genRandomPosition() {
        return RANDOM.nextInt((NUMBER_GIF - 1));
    }
}
