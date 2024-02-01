package com.pricemanager.infrastructure.persistence;

import com.pricemanager.domain.Price;
import com.pricemanager.domain.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Repository
public class PriceRepositoryImpl implements PriceRepository {

    private final PriceJpaRepository priceJPARepository;
    private final ConversionService conversionService;

    @Override
    public Price findHighestPriorityPrice(LocalDateTime date, Long productId, Long brandId) {
        PriceEntity price = priceJPARepository.findHighestPriorityPrice(date, productId, brandId).orElse(null);
        return conversionService.convert(price, Price.class);
    }
}
