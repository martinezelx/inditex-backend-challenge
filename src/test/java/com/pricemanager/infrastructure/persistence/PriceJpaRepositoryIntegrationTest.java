package com.pricemanager.infrastructure.persistence;

import static org.junit.jupiter.api.Assertions.*;

import com.pricemanager.utils.TestConstants;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureTestDatabase
class PriceJpaRepositoryIntegrationTest {

  @Autowired private PriceJpaRepository priceJpaRepository;

  @Test
  void whenFindHighestPriorityPrice_thenReturnCorrectPrice() {

    Optional<PriceEntity> result =
        priceJpaRepository.findHighestPriorityPrice(
            LocalDateTime.parse(TestConstants.VALID_DATE, DateTimeFormatter.ISO_DATE_TIME),
            Long.valueOf(TestConstants.PRODUCT_ID),
            Long.valueOf(TestConstants.BRAND_ID));

    assertTrue(result.isPresent());
    assertEquals(35.5, result.get().getPrice());
    assertEquals(35455L, result.get().getProductId().longValue());
    assertEquals(1L, result.get().getBrandId().longValue());
    assertEquals(Integer.valueOf(1), result.get().getPriceList());
  }

  @Test
  void whenFindHighestPriorityPriceWithNonMatchingDate_thenNoResult() {
    Optional<PriceEntity> result =
        priceJpaRepository.findHighestPriorityPrice(
            LocalDateTime.parse(
                TestConstants.NON_EXISTENT_VALID_DATE, DateTimeFormatter.ISO_DATE_TIME),
            Long.valueOf(TestConstants.PRODUCT_ID),
            Long.valueOf(TestConstants.BRAND_ID));
    assertFalse(result.isPresent());
  }

  @Test
  void whenFindHighestPriorityPriceWithNonExistentProductId_thenNoResult() {
    Optional<PriceEntity> result =
        priceJpaRepository.findHighestPriorityPrice(
            LocalDateTime.parse(TestConstants.VALID_DATE, DateTimeFormatter.ISO_DATE_TIME),
            Long.valueOf(TestConstants.NON_EXISTENT_PRODUCT_ID),
            Long.valueOf(TestConstants.BRAND_ID));
    assertFalse(result.isPresent());
  }

  @Test
  void whenFindHighestPriorityPriceWithNonExistentBrandId_thenNoResult() {
    Optional<PriceEntity> result =
        priceJpaRepository.findHighestPriorityPrice(
            LocalDateTime.parse(TestConstants.VALID_DATE, DateTimeFormatter.ISO_DATE_TIME),
            Long.valueOf(TestConstants.PRODUCT_ID),
            Long.valueOf(TestConstants.NON_EXISTENT_BRAND_ID));
    assertFalse(result.isPresent());
  }
}
