package com.pricemanager.infrastructure.rest;

import com.pricemanager.application.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/prices")
public class PriceManagerController {

    @Autowired
    private PriceService priceService;

    @GetMapping
    public PriceDto getPrice(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
                             @RequestParam("productId") Long productId,
                             @RequestParam("brandId") Long brandId) {
        return priceService.findPrice(date, productId, brandId);
    }
}
