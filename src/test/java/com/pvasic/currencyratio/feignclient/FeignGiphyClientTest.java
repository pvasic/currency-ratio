package com.pvasic.currencyratio.feignclient;

import com.pvasic.currencyratio.AbstractTestData;
import com.pvasic.currencyratio.service.GifService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

class FeignGiphyClientTest extends AbstractTestData {
    private static final String GIF_URL = "TEST_GIF_URL";

    @Autowired
    private GifService gifService;

    @MockBean
    private FeignGiphyClient giphyClient;

    @Test
    void getGifByPhrase() {
        Mockito.when(giphyClient.getGifByPhrase(anyString(), anyString(), anyInt()))
                .thenReturn(createGif(GIF_URL));
        assertEquals(GIF_URL, getGifUrl(gifService.getGif(true)));
    }
}
