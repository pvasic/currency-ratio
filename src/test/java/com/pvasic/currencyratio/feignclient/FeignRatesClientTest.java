package com.pvasic.currencyratio.feignclient;

import com.pvasic.currencyratio.model.ExchangeRate;
import com.pvasic.currencyratio.service.RatesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class FeignRatesClientTest {

    @Value("${openexchangerates.currency}")
    private String baseCurrency;

    private final Double LOW_RATE = 60.000000D;
    private final Double HIGH_RATE = 60.000001D;

    private ExchangeRate RICH_CURRENT_RATE;
    private ExchangeRate RICH_YESTERDAY_RATE;

    private ExchangeRate BROKE_CURRENT_RATE;
    private ExchangeRate BROKE_YESTERDAY_RATE;

    @Autowired
    private RatesService ratesService;

    @MockBean
    private FeignRatesClient ratesClient;

    @BeforeEach
    void setUp() {
        Map<String, Double> richCurrentRateMap = new HashMap<>();
        richCurrentRateMap.put(baseCurrency, HIGH_RATE);
        RICH_CURRENT_RATE = new ExchangeRate(richCurrentRateMap);

        Map<String, Double> richYesterdayRateMap = new HashMap<>();
        richYesterdayRateMap.put(baseCurrency, LOW_RATE);
        RICH_YESTERDAY_RATE = new ExchangeRate(richYesterdayRateMap);

        Map<String, Double> brokeCurrentRateMap = new HashMap<>();
        brokeCurrentRateMap.put(baseCurrency, LOW_RATE);
        BROKE_CURRENT_RATE = new ExchangeRate(brokeCurrentRateMap);

        Map<String, Double> brokeYesterdayRateMap = new HashMap<>();
        brokeYesterdayRateMap.put(baseCurrency, HIGH_RATE);
        BROKE_YESTERDAY_RATE = new ExchangeRate(brokeYesterdayRateMap);
    }

    @Test
    void isRichRate() {
        Mockito.when(ratesClient.getLatestRate(anyString()))
                .thenReturn(RICH_CURRENT_RATE);
        Mockito.when(ratesClient.getHistoricalRate(anyString(), anyString()))
                .thenReturn(RICH_YESTERDAY_RATE);
        assertTrue(ratesService.isRiseRate(baseCurrency));
    }

    @Test
    void isBrokeRate() {
        Mockito.when(ratesClient.getLatestRate(anyString()))
                .thenReturn(BROKE_CURRENT_RATE);
        Mockito.when(ratesClient.getHistoricalRate(anyString(), anyString()))
                .thenReturn(BROKE_YESTERDAY_RATE);
        assertFalse(ratesService.isRiseRate(baseCurrency));
    }
}
