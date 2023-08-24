package com.cdac.hungryhaven.dto;

import java.util.ArrayList;
import java.util.List;

import com.cdac.hungryhaven.models.ItemEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
	
	private Long cart_id;
	
	private Long restaurantId;
	
	private Long userId;
	
	private List<ItemEntity> items = new ArrayList();
	
	private float total;

}
