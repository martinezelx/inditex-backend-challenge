package com.pricemanager.application;

import com.pricemanager.infrastructure.rest.PriceDto;

import java.time.LocalDateTime;

public interface PriceService {

    PriceDto findPrice(LocalDateTime date, Long productId, Long brandId);
}
