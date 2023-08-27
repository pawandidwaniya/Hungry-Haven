package com.cdac.hungryhaven.exchanges;

import java.util.List;

import com.cdac.hungryhaven.dto.Order;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetOrdersResponse {

  @NotNull
  List<Order> order;

  @NotNull
  Long restaurantId;
}
