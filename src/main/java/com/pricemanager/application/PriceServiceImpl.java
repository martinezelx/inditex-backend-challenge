package com.pricemanager.application;

import com.pricemanager.domain.Price;
import com.pricemanager.domain.PriceRepository;
import com.pricemanager.infrastructure.rest.PriceResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    public PriceResponseDto findPrice(LocalDateTime date, Long productId, Long brandId) {
        // TODO mapper
        Price price = priceRepository.findHighestPriorityPrice(date, productId, brandId);
        return toDto(price);
    }

    private PriceResponseDto toDto(Price price) {
        if (price == null) {
            return null;
        }

        return new PriceResponseDto(
                price.getProductId(),
                price.getBrandId(),
                price.getPriceList(),
                price.getStartDate(),
                price.getEndDate(),
                price.getPrice()
        );
    }
}
