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
                source.getProductId(),
                source.getBrandId(),
                source.getPriceList(),
                source.getStartDate(),
                source.getEndDate(),
                source.getPrice()
        );
    }
}
