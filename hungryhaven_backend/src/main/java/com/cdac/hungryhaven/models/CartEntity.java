package com.cdac.hungryhaven.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "cart")
@Data
@NoArgsConstructor
public class CartEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cart_id;
	
	@NotBlank
	private Long restaurantId;
	
	@NotBlank
	private Long userId;
	

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
	private List<ItemEntity> items = new ArrayList();
	
	@NotBlank
	private float total = 0;
	
	public void addItem(ItemEntity item) {
		items.add(item);
		total += item.getPrice();
	}
	
	public void removeItem(ItemEntity item) {
		boolean removed = items.remove(item);
		if(removed)
		{
			total -=item.getPrice();
		}
	}
	
	public void clearCart() {
		if(items.size()>0) {
			items.clear();
			total = 0;
		}
	}
		
}
