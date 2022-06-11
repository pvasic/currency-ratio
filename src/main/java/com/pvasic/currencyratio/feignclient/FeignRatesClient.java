package com.pvasic.currencyratio.feignclient;

import com.pvasic.currencyratio.model.ExchangeRate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "OerClient", url = "${openexchangerates.url}")
public interface FeignRatesClient {
    @GetMapping(value = "/latest.json")
    ExchangeRate getLatestRate(@RequestParam("app_id") String appId);

    @GetMapping(value = "/historical/{date}.json")
    ExchangeRate getHistoricalRate(@PathVariable String date,
                                   @RequestParam("app_id") String appId);
}
