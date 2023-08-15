package com.cdac.hungryhaven.repositories;

import com.cdac.hungryhaven.models.QuantityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuantityRepository extends JpaRepository<QuantityEntity, Long> {
    // Additional query methods can be added here if needed

    Optional<QuantityEntity> findByItemIdAndRestaurantId(Long itemId, Long restaurantId);
}
