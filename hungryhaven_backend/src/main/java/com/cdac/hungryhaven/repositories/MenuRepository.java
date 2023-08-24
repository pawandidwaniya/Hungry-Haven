package com.cdac.hungryhaven.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.cdac.hungryhaven.models.MenuEntity;

public interface MenuRepository extends JpaRepository<MenuEntity, Long> {

	Optional<MenuEntity> findMenuByRestaurantId(Long restaurantId);

	Optional<MenuEntity> findMenusByItemsIdIn(List<Long> itemIds);

}
