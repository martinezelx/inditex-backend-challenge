package com.pricemanager.application;

import com.pricemanager.domain.Price;
import com.pricemanager.domain.PriceRepository;
import com.pricemanager.infrastructure.rest.PriceResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;
    private final ConversionService conversionService;

    public PriceResponseDto findPrice(LocalDateTime date, Long productId, Long brandId) {
        Price price = priceRepository.findHighestPriorityPrice(date, productId, brandId);
        return conversionService.convert(price, PriceResponseDto.class);
    }
}
