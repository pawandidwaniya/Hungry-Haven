package com.cdac.hungryhaven.repositoryservices;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cdac.hungryhaven.dto.Cart;
import com.cdac.hungryhaven.exceptions.CartNotFoundException;
import com.cdac.hungryhaven.models.ItemEntity;


@Service
public interface CartRepositoryService {
	
	Long createCart(Cart cart);
	
	Optional<Cart> findCartByUserId(Long userId);
	
	Cart findCartByCartId(Long cartId) throws CartNotFoundException;
	
	Cart addItem(ItemEntity item, Long cartId, Long restaurantId) throws CartNotFoundException;
	
	Cart removeItem(ItemEntity item, Long cartId, Long restaurantId) throws CartNotFoundException;
	
}
