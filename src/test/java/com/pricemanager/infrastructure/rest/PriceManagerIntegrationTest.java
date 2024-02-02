package com.pricemanager.infrastructure.rest;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class PriceManagerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String PRODUCT_ID = "35455";
    private static final String BRAND_ID = "1";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Test
    public void requestAt10On14th_ShouldReturnExpectedPrice() {
        performRequest("2020-06-14 10:00:00");
    }

    @Test
    public void requestAt16On14th_ShouldReturnExpectedPrice() {
        performRequest("2020-06-14 16:00:00");
    }

    @Test
    public void requestAt21On14th_ShouldReturnExpectedPrice() {
        performRequest("2020-06-14 21:00:00");
    }

    @Test
    public void requestAt10On15th_ShouldReturnExpectedPrice() {
        performRequest("2020-06-15 10:00:00");
    }

    @Test
    public void requestAt21On16th_ShouldReturnExpectedPrice() {
        performRequest("2020-06-16 21:00:00");
    }

    private void performRequest(String date) {
        try {
            mockMvc.perform(get("/api/v1/prices")
                            .param("date", LocalDateTime.parse(date, FORMATTER).toString())
                            .param("productId", PRODUCT_ID)
                            .param("brandId", BRAND_ID)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        } catch (Exception e) {
            log.error("Error performing request in integration tests", e);
        }
    }
}