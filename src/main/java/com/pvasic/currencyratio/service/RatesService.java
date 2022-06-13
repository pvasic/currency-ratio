package com.pvasic.currencyratio.service;

import com.pvasic.currencyratio.feignclient.FeignRatesClient;
import com.pvasic.currencyratio.model.ExchangeRate;
import com.pvasic.currencyratio.util.RateUtil;
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

    public boolean isRiseRate(String code) {
        double currentRate = ratesClient.getLatestRate(yourId).getRates().get(code);
        double yesterdayRate = ratesClient.getHistoricalRate(getYesterdayDate(), yourId).getRates().get(code);
        return RateUtil.isRise(currentRate, yesterdayRate);
    }

    public List<String> getCodes() {
        ExchangeRate currentRate = ratesClient.getLatestRate(yourId);
        return new ArrayList<>(currentRate.getRates().keySet());
    }
}
