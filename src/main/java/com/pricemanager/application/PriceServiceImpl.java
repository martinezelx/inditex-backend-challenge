package com.pricemanager.application;

import com.pricemanager.domain.Price;
import com.pricemanager.domain.PriceRepository;
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
        Price price = priceRepository.findHighestPriorityPrice(date, productId, brandId);
        return toDto(price);
    }

    private PriceDto toDto(Price price) {
        if (price == null) {
            return null;
        }

        return new PriceDto(
                price.getId(),
                price.getBrandId(),
                price.getStartDate(),
                price.getEndDate(),
                price.getPriceList(),
                price.getProductId(),
                price.getPriority(),
                price.getPrice(),
                price.getCurrency()
        );
    }
}