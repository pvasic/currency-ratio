package com.pvasic.currencyratio.service;

import com.pvasic.currencyratio.error.IllegalRequestDataException;
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
        ExchangeRate cRate = ratesClient.getLatestRate(yourId);
        ExchangeRate yRate = ratesClient.getHistoricalRate(getYesterdayDate(), yourId);

        if (cRate == null || yRate == null) {
            throw new IllegalRequestDataException("Rates is null");
        }

        double currentRate = cRate.getRates().get(code);
        double yesterdayRate = yRate.getRates().get(code);

        return RateUtil.isRise(currentRate, yesterdayRate);
    }

    public List<String> getCodes() {
        ExchangeRate currentRate = ratesClient.getLatestRate(yourId);
        return new ArrayList<>(currentRate.getRates().keySet());
    }
}
