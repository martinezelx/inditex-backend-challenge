package com.pricemanager.domain;

import java.time.LocalDateTime;

public interface PriceRepository {

    Price findHighestPriorityPrice(LocalDateTime date, Long productId, Long brandId);
}
