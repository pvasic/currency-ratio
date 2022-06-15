package com.pvasic.currencyratio.controller;

import com.pvasic.currencyratio.AbstractTestData;
import com.pvasic.currencyratio.feignclient.FeignGiphyClient;
import com.pvasic.currencyratio.feignclient.FeignRatesClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
class BaseControllerTest extends AbstractTestData {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FeignRatesClient ratesClient;

    @MockBean
    private FeignGiphyClient giphyClient;


    @Test
    void getRichGif() throws Exception {
        Mockito.when(ratesClient.getLatestRate(anyString()))
                .thenReturn(getRichCurrentRate());
        Mockito.when(ratesClient.getHistoricalRate(anyString(), anyString()))
                .thenReturn(getRichYesterdayRate());

        Mockito.when(giphyClient.getGifByPhrase(anyString(), anyString(), anyInt()))
                .thenReturn(createArrayGifUrl(richUrl));

        perform(MockMvcRequestBuilders.get(BaseController.URL + "/gifs/" + baseCurrency))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$").value(richUrl));
    }

    @Test
    void getBrokeGif() throws Exception {
        Mockito.when(ratesClient.getLatestRate(anyString()))
                .thenReturn(getBrokeCurrentRate());
        Mockito.when(ratesClient.getHistoricalRate(anyString(), anyString()))
                .thenReturn(getBrokeYesterdayRate());

        Mockito.when(giphyClient.getGifByPhrase(anyString(), anyString(), anyInt()))
                .thenReturn(createArrayGifUrl(brokeUrl));

        perform(MockMvcRequestBuilders.get(BaseController.URL + "/gifs/" + baseCurrency))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$").value(brokeUrl));
    }

    private ResultActions perform(MockHttpServletRequestBuilder builder) throws Exception {
        return mockMvc.perform(builder);
    }
}
