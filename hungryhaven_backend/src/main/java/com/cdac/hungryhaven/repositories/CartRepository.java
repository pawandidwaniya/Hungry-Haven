package com.cdac.hungryhaven.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.hungryhaven.models.CartEntity;

public interface CartRepository extends JpaRepository<CartEntity, Long>{

	Optional<CartEntity> findByUserId(Long id);
}
