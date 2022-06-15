package com.pvasic.currencyratio.feignclient;

import com.pvasic.currencyratio.AbstractTestData;
import com.pvasic.currencyratio.service.RatesService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;

class FeignRatesClientTest extends AbstractTestData {
    @Autowired
    private RatesService ratesService;

    @MockBean
    private FeignRatesClient ratesClient;

    @Test
    void isRichRate() {
        Mockito.when(ratesClient.getLatestRate(anyString()))
                .thenReturn(getRichCurrentRate());
        Mockito.when(ratesClient.getHistoricalRate(anyString(), anyString()))
                .thenReturn(getRichYesterdayRate());
        assertTrue(ratesService.isRiseRate(baseCurrency));
    }

    @Test
    void isBrokeRate() {
        Mockito.when(ratesClient.getLatestRate(anyString()))
                .thenReturn(getBrokeCurrentRate());
        Mockito.when(ratesClient.getHistoricalRate(anyString(), anyString()))
                .thenReturn(getBrokeYesterdayRate());
        assertFalse(ratesService.isRiseRate(baseCurrency));
    }
}
