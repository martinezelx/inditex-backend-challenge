package com.pricemanager.infrastructure.persistence;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "prices")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "brand_id")
  private Long brandId;

  @Column(name = "start_date")
  private LocalDateTime startDate;

  @Column(name = "end_date")
  private LocalDateTime endDate;

  @Column(name = "price_list")
  private Integer priceList;

  @Column(name = "product_id")
  private Long productId;

  @Column(name = "priority")
  private Integer priority;

  @Column(name = "price")
  private Double price;

  @Column(name = "curr")
  private String currency;
}
