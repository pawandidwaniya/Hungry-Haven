package com.cdac.hungryhaven.repositories;

import com.cdac.hungryhaven.models.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Long> {
}
