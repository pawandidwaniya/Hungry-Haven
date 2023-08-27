package com.cdac.hungryhaven.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemQuantity {
  @Id
  private Long id;

  @NotNull
  private Long itemId;

  @NotNull
  private Long restaurantId;

  @NotNull
  private int quantity;
}
