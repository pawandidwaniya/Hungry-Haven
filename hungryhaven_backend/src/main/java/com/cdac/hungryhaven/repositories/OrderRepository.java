package com.cdac.hungryhaven.repositories;

import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.hungryhaven.models.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
	 Optional<List<OrderEntity>> findByRestaurantId(Long restaurantId);

}
