package com.pvasic.currencyratio.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RateUtil {
    private static final int SCALE_ROUND = 7;

    public static boolean isRise(double currentRate, double yesterdayRate) {
        BigDecimal current = BigDecimal.valueOf(currentRate).setScale(SCALE_ROUND, RoundingMode.HALF_EVEN);
        BigDecimal yesterday = BigDecimal.valueOf(yesterdayRate).setScale(SCALE_ROUND, RoundingMode.HALF_EVEN);
        return !(current.compareTo(yesterday) <= 0);
    }
}
