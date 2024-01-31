package com.pricemanager.application;

import com.pricemanager.infrastructure.persistence.PriceEntity;
import com.pricemanager.infrastructure.persistence.PriceRepository;
import com.pricemanager.infrastructure.rest.PriceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PriceServiceImpl implements PriceService {

    @Autowired
    private PriceRepository priceRepository;

    public PriceDto findPrice(LocalDateTime date, Long productId, Long brandId) {
        // TODO mapper
        PriceEntity priceEntity =
                priceRepository.findHighestPriorityPrice(
                        date, productId, brandId).stream().findFirst().orElse(null);
        return toDto(priceEntity);
    }

    private PriceDto toDto(PriceEntity priceEntity) {
        if (priceEntity == null) {
            return null;
        }

        return new PriceDto(
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