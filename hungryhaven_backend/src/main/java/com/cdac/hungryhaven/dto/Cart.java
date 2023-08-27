package com.cdac.hungryhaven.dto;

import java.util.ArrayList;
import java.util.List;

import com.cdac.hungryhaven.models.ItemEntity;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
	
	private Long cart_id;

	@NotBlank(message = "Resturant Id must be provided")
	private Long restaurantId;
	@NotBlank(message = "User Id must be provided")
	private Long userId;
	
	private List<ItemEntity> items = new ArrayList();
	
	private float total;

}
