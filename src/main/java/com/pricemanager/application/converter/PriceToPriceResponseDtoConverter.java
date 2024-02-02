package com.pricemanager.application.converter;

import com.pricemanager.domain.Price;
import com.pricemanager.infrastructure.rest.PriceResponseDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PriceToPriceResponseDtoConverter implements Converter<Price, PriceResponseDto> {
    @Override
    public PriceResponseDto convert(Price source) {
        return new PriceResponseDto(
                source.productId(),
                source.brandId(),
                source.priceList(),
                source.startDate(),
                source.endDate(),
                source.price()
        );
    }
}
