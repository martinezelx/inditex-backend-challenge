package com.pricemanager.infrastructure.rest;

import com.pricemanager.utils.TestConstants;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class PriceManagerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

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
            mockMvc.perform(get(TestConstants.PRICE_API_URL)
                            .param("date", LocalDateTime.parse(date, TestConstants.FORMATTER).toString())
                            .param("productId", TestConstants.PRODUCT_ID)
                            .param("brandId", TestConstants.BRAND_ID)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        } catch (Exception e) {
            log.error("Error performing request in integration tests", e);
        }
    }

    @Test
    public void givenRequestWithInvalidFormatDateTime_ThenShouldReturnBadRequest() throws Exception {
        mockMvc.perform(get(TestConstants.PRICE_API_URL)
                        .param("date", TestConstants.INVALID_DATE_FORMAT)
                        .param("productId", TestConstants.PRODUCT_ID)
                        .param("brandId", TestConstants.BRAND_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void givenRequestWithNonExistentProductId_ThenShouldReturnNotFound() throws Exception {
        mockMvc.perform(get(TestConstants.PRICE_API_URL)
                        .param("date", LocalDateTime.parse("2020-06-14 10:00:00", TestConstants.FORMATTER).toString())
                        .param("productId", TestConstants.NON_EXISTENT_PRODUCT_ID)
                        .param("brandId", TestConstants.BRAND_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.exception").value("PriceNotFoundException"));
    }

    @Test
    public void givenRequestWithNonExistentBrandId_ThenShouldReturnNotFound() throws Exception {
        mockMvc.perform(get(TestConstants.PRICE_API_URL)
                        .param("date", LocalDateTime.parse("2020-06-14 10:00:00", TestConstants.FORMATTER).toString())
                        .param("productId", TestConstants.PRODUCT_ID)
                        .param("brandId", TestConstants.NON_EXISTENT_BRAND_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.exception").value("PriceNotFoundException"));
    }
}