package com.cdac.hungryhaven.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.cdac.hungryhaven.models.ItemEntity;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
	  @NotNull
	  private Long id;

	  @NotNull
	  private Long restaurantId;

	  @NotNull
	  private Long userId;

	  @NotNull
	  private List<ItemEntity> items = new ArrayList();

	  @NotNull
	  private int total;

	  @NotNull
	  private Instant timePlaced;

	  @NotNull
	  private String status;
}
