package com.pricemanager.infrastructure.persistence.converter;

import com.pricemanager.domain.Price;
import com.pricemanager.infrastructure.persistence.PriceEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PriceEntityToPriceConverter implements Converter<PriceEntity, Price> {
    @Override
    public Price convert(PriceEntity source) {
        return new Price(
                source.getBrandId(),
                source.getStartDate(),
                source.getEndDate(),
                source.getPriceList(),
                source.getProductId(),
                source.getPriority(),
                source.getPrice(),
                source.getCurrency()
        );
    }
}
