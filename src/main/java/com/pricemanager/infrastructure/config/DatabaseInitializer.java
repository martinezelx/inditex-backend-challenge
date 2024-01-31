package com.pricemanager.infrastructure.config;

import com.pricemanager.infrastructure.persistence.PriceEntity;
import com.pricemanager.infrastructure.persistence.PriceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Configuration
public class DatabaseInitializer {

    @Bean
    public CommandLineRunner initDatabase(PriceRepository priceRepository) {
        return args -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");

            priceRepository.saveAll(Arrays.asList(
                    new PriceEntity(10L, 1L, LocalDateTime.parse("2020-06-14-00.00.00", formatter),
                            LocalDateTime.parse("2020-12-31-23.59.59", formatter), 1, 35455L, 0, 35.50, "EUR"),
                    new PriceEntity(11L, 1L, LocalDateTime.parse("2020-06-14-15.00.00", formatter),
                            LocalDateTime.parse("2020-06-14-18.30.00", formatter), 2, 35455L, 1, 25.45, "EUR"),
                    new PriceEntity(12L, 1L, LocalDateTime.parse("2020-06-15-00.00.00", formatter),
                            LocalDateTime.parse("2020-06-15-11.00.00", formatter), 3, 35455L, 1, 30.50, "EUR"),
                    new PriceEntity(13L, 1L, LocalDateTime.parse("2020-06-15-16.00.00", formatter),
                            LocalDateTime.parse("2020-12-31-23.59.59", formatter), 4, 35455L, 1, 38.95, "EUR")
            ));
        };
    }
}