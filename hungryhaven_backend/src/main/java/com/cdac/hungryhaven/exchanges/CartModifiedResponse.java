package com.cdac.hungryhaven.exchanges;

import com.cdac.hungryhaven.dto.Cart;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartModifiedResponse {
	  @NotNull
	  private Cart cart;

	  @NotNull
	  private int cartResponseType;
}
