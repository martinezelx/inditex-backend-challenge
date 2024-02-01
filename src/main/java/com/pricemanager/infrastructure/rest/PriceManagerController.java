package com.pricemanager.infrastructure.rest;

import com.pricemanager.application.PriceService;
import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/prices")
public class PriceManagerController {

    private final PriceService priceService;

    @GetMapping
    @Operation(summary = "Returns the highest priority price for a given product, brand and date.")
    @Timed(value = "prices.timed", description = "Time taken to find highest priority price")
    public ResponseEntity<PriceResponseDto> getPrice(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @NotNull(message = "Date cannot be null") LocalDateTime date,
            @RequestParam @NotNull(message = "Product ID cannot be null") Long productId,
            @RequestParam @NotNull(message = "Brand ID cannot be null") Long brandId) {
        return ResponseEntity.ok(priceService.findPrice(date, productId, brandId));
    }
}
