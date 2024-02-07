package com.pricemanager.application;

import com.pricemanager.domain.PriceRepository;
import com.pricemanager.infrastructure.rest.PriceResponseDto;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class PriceServiceImpl implements PriceService {

  private final PriceRepository priceRepository;
  private final ConversionService conversionService;

  public PriceResponseDto findPrice(LocalDateTime date, Long productId, Long brandId) {
    log.info("Finding price for date: {}, productId: {}, brandId: {}", date, productId, brandId);
    return conversionService.convert(
        priceRepository.findHighestPriorityPrice(date, productId, brandId), PriceResponseDto.class);
  }
}
