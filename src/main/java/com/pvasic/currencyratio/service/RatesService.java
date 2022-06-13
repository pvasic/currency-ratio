package com.pvasic.currencyratio.service;

import com.pvasic.currencyratio.feignclient.FeignRatesClient;
import com.pvasic.currencyratio.model.ExchangeRate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.pvasic.currencyratio.util.DateUtil.getYesterdayDate;

@Service
@RequiredArgsConstructor
public class RatesService {
    private final FeignRatesClient ratesClient;

    @Value("${openexchangerates.your-id}")
    private String yourId;

    public boolean isRateRise(String code) {
        ExchangeRate currentRate = ratesClient.getLatestRate(yourId);
        ExchangeRate yesterdayRate = ratesClient.getHistoricalRate(getYesterdayDate(), yourId);
        return currentRate.getRates().get(code) > yesterdayRate.getRates().get(code);
    }

    public List<String> getCodes() {
        ExchangeRate currentRate = ratesClient.getLatestRate(yourId);
        return new ArrayList<>(currentRate.getRates().keySet());
    }
}
