package com.cdac.hungryhaven.repositoryservices;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.hungryhaven.dto.Cart;
import com.cdac.hungryhaven.exceptions.CartNotFoundException;
import com.cdac.hungryhaven.models.CartEntity;
import com.cdac.hungryhaven.models.ItemEntity;
import com.cdac.hungryhaven.repositories.CartRepository;

import jakarta.inject.Provider;

@Service
public class CartRepositoryServiceImpl implements CartRepositoryService {
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	Provider<ModelMapper> modelMapperProvider;
	
	@Override
	public Long createCart(Cart cart) {
		ModelMapper modelMapper = modelMapperProvider.get();
		CartEntity cartEntity = modelMapper.map(cart, CartEntity.class);
		cartRepository.save(cartEntity);
		return cart.getRestaurantId();
	}

	@Override
	public Optional<Cart> findCartByUserId(Long userId) {
		ModelMapper modelMapper = modelMapperProvider.get();
		
		Optional<CartEntity> optionalCartEntity = cartRepository.findByUserId(userId);
		
		Optional<Cart> optionalCart = Optional.empty();
		
		Cart cart = null;
		
		if (optionalCartEntity.isPresent()) {
		      cart = modelMapper.map(optionalCartEntity.get(), Cart.class);
		      optionalCart = Optional.of(cart);
		    } else {
		      throw new CartNotFoundException();
		    }
		    return optionalCart;
	}

	@Override
	public Cart findCartByCartId(Long cartId) throws CartNotFoundException {
		
		ModelMapper modelMapper = modelMapperProvider.get();
	    Optional<CartEntity> cartEntity = cartRepository.findById(cartId);
	    Cart cart = null;
	    
	    if (cartEntity.isPresent()) {
	      cart = modelMapper.map(cartEntity.get(), Cart.class);
	    } 
	    else {	
	      throw new CartNotFoundException();
	    }
	    return cart;
	}

	@Override
	public Cart addItem(ItemEntity item, Long cartId, Long restaurantId) throws CartNotFoundException {
		
		ModelMapper modelMapper = modelMapperProvider.get();
	    
		Optional<CartEntity> optionalCartEntity = cartRepository.findById(cartId);
	    
		Cart cart = null;
	    
		if (optionalCartEntity.isPresent()) {
	      CartEntity cartEntity = optionalCartEntity.get();
	      cartEntity.addItem(item);
	      
	      if (cartEntity.getItems().size() == 1) {
	        cartEntity.setRestaurantId(restaurantId);
	      }
	      
	      cartRepository.save(cartEntity);
	      
	      cart = modelMapper.map(cartEntity, Cart.class);
	    } 
		
		else {
	      throw new CartNotFoundException();
	    }
	    return cart;
		
	}

	@Override
	public Cart removeItem(ItemEntity item, Long cartId, Long restaurantId) throws CartNotFoundException {
		
		ModelMapper modelMapper = modelMapperProvider.get();
	    
		Optional<CartEntity> optionalCartEntity = cartRepository.findById(cartId);
	    
		Cart cart = null;
		
		if (optionalCartEntity.isPresent()) {
		      CartEntity cartEntity = optionalCartEntity.get();
		      
		      List<ItemEntity> itemList = cartEntity.getItems();
		      
		      for (ItemEntity itemInCart : itemList) {
		        
		    	  if (item.getId().equals(itemInCart.getId())) {
		          cartEntity.removeItem(item);
		          break;
		        }
		      }
		      if (cartEntity.getItems().size() == 0) {
		        cartEntity.setRestaurantId(null);
		      }
		      cartRepository.save(cartEntity);
		      cart = modelMapper.map(cartEntity, Cart.class);
		    } else {
		      throw new CartNotFoundException();
		    }
		    return cart;
		
	}

}
