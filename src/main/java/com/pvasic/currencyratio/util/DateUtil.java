package com.pvasic.currencyratio.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    private static final long NUMBERS_OF_DAYS_AGO = 1;

    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

    public static String getYesterdayDate() {
        return LocalDateTime.now().minusDays(NUMBERS_OF_DAYS_AGO).format(DATE_TIME_FORMATTER);
    }
}
