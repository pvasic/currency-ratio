package com.pvasic.currencyratio;

import com.pvasic.currencyratio.model.ExchangeRate;
import com.pvasic.currencyratio.model.gif.Data;
import com.pvasic.currencyratio.model.gif.Gif;
import com.pvasic.currencyratio.model.gif.Images;
import com.pvasic.currencyratio.model.gif.Original;
import com.pvasic.currencyratio.service.GifService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;
import java.util.stream.IntStream;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public abstract class AbstractTestData {
    @Value("${openexchangerates.currency}")
    protected String baseCurrency;

    @Value("${giphy.rich}")
    protected String richUrl;
    @Value("${giphy.broke}")
    protected String brokeUrl;

    private final Double LOW_RATE = 60.000000D;
    private final Double HIGH_RATE = 60.000001D;

    protected ExchangeRate getRichCurrentRate() {
        return new ExchangeRate(Map.of(baseCurrency, HIGH_RATE));
    }

    protected ExchangeRate getRichYesterdayRate() {
        return new ExchangeRate(Map.of(baseCurrency, LOW_RATE));
    }

    protected ExchangeRate getBrokeCurrentRate() {
        return new ExchangeRate(Map.of(baseCurrency, LOW_RATE));
    }

    protected ExchangeRate getBrokeYesterdayRate() {
        return new ExchangeRate(Map.of(baseCurrency, HIGH_RATE));
    }

    protected Gif createGif(String gifUrl) {
        return new Gif(new Data[]{new Data(new Images(new Original(gifUrl)))});
    }

    protected Gif createArrayGifUrl(String gifUrl) {
        return new Gif(IntStream.range(1, GifService.NUMBER_GIF).boxed()
                .map(i -> new Data(new Images(new Original(gifUrl))))
                .toArray(Data[]::new));
    }

    protected String getGifUrl(Gif gif) {
        return gif.getData()[0].getImages().getOriginal().getUrl();
    }
}
