package com.cdac.hungryhaven.repositories;

import com.cdac.hungryhaven.models.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<ItemEntity, String> {

    Optional<ItemEntity> findByItemId(String itemId);
}
