package com.pricemanager.infrastructure.rest;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.pricemanager.application.PriceService;
import com.pricemanager.infrastructure.exception.ErrorMessages;
import com.pricemanager.infrastructure.persistence.exception.PriceNotFoundException;
import com.pricemanager.utils.TestConstants;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PriceManagerController.class)
public class PriceManagerControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private PriceService priceService;

  private LocalDateTime date;
  private Long productId;
  private Long brandId;

  @BeforeEach
  public void setUp() {
    date = LocalDateTime.now();
    productId = 1L;
    brandId = 1L;
  }

  @Test
  public void getPrice_ShouldReturnPriceResponseDto() throws Exception {
    PriceResponseDto priceResponseDto =
        new PriceResponseDto(productId, brandId, 1, date, date, 35.50);

    given(priceService.findPrice(date, productId, brandId)).willReturn(priceResponseDto);

    mockMvc
        .perform(
            get(TestConstants.PRICE_API_URL)
                .param("date", date.toString())
                .param("productId", productId.toString())
                .param("brandId", brandId.toString())
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.price").value(35.50));
  }

  @Test
  public void getPrice_WhenDateIsMissing_ShouldReturnBadRequest() throws Exception {
    mockMvc
        .perform(
            get(TestConstants.PRICE_API_URL)
                .param("productId", productId.toString())
                .param("brandId", brandId.toString())
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void getPrice_WhenProductIdIsMissing_ShouldReturnBadRequest() throws Exception {
    mockMvc
        .perform(
            get(TestConstants.PRICE_API_URL)
                .param("date", date.toString())
                .param("brandId", brandId.toString())
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void getPrice_WhenBrandIdIsMissing_ShouldReturnBadRequest() throws Exception {
    mockMvc
        .perform(
            get(TestConstants.PRICE_API_URL)
                .param("date", date.toString())
                .param("productId", productId.toString())
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void getPrice_WhenPriceNotFound_ShouldReturnNotFound() throws Exception {
    given(priceService.findPrice(date, productId, brandId))
        .willThrow(new PriceNotFoundException(ErrorMessages.PRICE_NOT_FOUND));

    mockMvc
        .perform(
            get(TestConstants.PRICE_API_URL)
                .param("date", date.toString())
                .param("productId", productId.toString())
                .param("brandId", brandId.toString())
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.message").value(ErrorMessages.PRICE_NOT_FOUND));
  }
}
