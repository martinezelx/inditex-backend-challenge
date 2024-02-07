package com.pricemanager.application;

import com.pricemanager.infrastructure.rest.PriceResponseDto;
import java.time.LocalDateTime;

public interface PriceService {

  PriceResponseDto findPrice(LocalDateTime date, Long productId, Long brandId);
}
