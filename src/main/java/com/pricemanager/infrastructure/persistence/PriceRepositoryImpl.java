package com.pricemanager.infrastructure.persistence;

import com.pricemanager.domain.Price;
import com.pricemanager.domain.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class PriceRepositoryImpl implements PriceRepository {

    @Autowired
    private PriceJpaRepository priceJPARepository;

    @Override
    public Price findHighestPriorityPrice(LocalDateTime date, Long productId, Long brandId) {
        // TODO mapper
        PriceEntity price = priceJPARepository.findHighestPriorityPrice(date,productId,brandId).orElse(null);
        return toDomain(price);
    }

   private Price toDomain(PriceEntity priceEntity) {
    if (priceEntity == null) {
        return null;
    }

    return new Price(
            priceEntity.getId(),
            priceEntity.getBrandId(),
            priceEntity.getStartDate(),
            priceEntity.getEndDate(),
            priceEntity.getPriceList(),
            priceEntity.getProductId(),
            priceEntity.getPriority(),
            priceEntity.getPrice(),
            priceEntity.getCurrency()
    );
}
}
