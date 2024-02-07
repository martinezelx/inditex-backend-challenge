package com.pricemanager.domain;

import java.time.LocalDateTime;

public record Price(
    Long brandId,
    LocalDateTime startDate,
    LocalDateTime endDate,
    Integer priceList,
    Long productId,
    Integer priority,
    Double price,
    String currency) {}
