package com.pricemanager.infrastructure.config;

import com.pricemanager.application.converter.PriceToPriceResponseDtoConverter;
import com.pricemanager.infrastructure.persistence.converter.PriceEntityToPriceConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final PriceToPriceResponseDtoConverter priceToPriceResponseDtoConverter;
    private final PriceEntityToPriceConverter priceEntityToPriceConverter;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(priceToPriceResponseDtoConverter);
        registry.addConverter(priceEntityToPriceConverter);
    }
}
