package com.pvasic.currencyratio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CurrencyRatioApplication {
    public static void main(String[] args) {
        SpringApplication.run(CurrencyRatioApplication.class, args);
    }

}
