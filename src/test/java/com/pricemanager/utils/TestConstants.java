package com.pricemanager.utils;

import java.time.format.DateTimeFormatter;

public final class TestConstants {

    private TestConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String PRICE_API_URL = "/api/v1/prices";
    public static final String PRODUCT_ID = "35455";
    public static final String BRAND_ID = "1";
    public static final String VALID_DATE = "2020-06-14T10:00:00";
    public static final String NON_EXISTENT_VALID_DATE = "2030-01-01T00:00:00";
    public static final String INVALID_DATE_FORMAT = "2030-01-01";
    public static final String NON_EXISTENT_PRODUCT_ID = "35000";
    public static final String NON_EXISTENT_BRAND_ID = "2";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
}
