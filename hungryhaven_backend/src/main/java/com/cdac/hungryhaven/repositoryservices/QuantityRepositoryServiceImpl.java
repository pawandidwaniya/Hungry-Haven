package com.cdac.hungryhaven.repositoryservices;

import com.cdac.hungryhaven.dto.ItemQuantity;
import com.cdac.hungryhaven.models.QuantityEntity;
import com.cdac.hungryhaven.repositories.QuantityRepository;

import jakarta.inject.Provider;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuantityRepositoryServiceImpl implements QuantityRepositoryService {
	
	  @Autowired
	  private QuantityRepository quantityRepository;

	  @Autowired
	  private Provider<ModelMapper> modelMapperProvider;

	@Override
	public ItemQuantity updateQuantity(Long itemId, Long restaurantId, int quantity) throws Exception {
	    ItemQuantity itemQuantity = null;
	    Optional<QuantityEntity> quantityEntityOptional = quantityRepository.findByItemIdAndRestaurantId(itemId, restaurantId);
	    ModelMapper modelMapper = modelMapperProvider.get();
	    if (quantityEntityOptional.isPresent()) {
	      QuantityEntity quantityEntity = quantityEntityOptional.get();
	      quantityEntity.setQuantity(quantity);
	      quantityRepository.save(quantityEntity);

	      itemQuantity = modelMapper.map(quantityEntity, ItemQuantity.class);

	    } else {
	      throw new Exception("Either restaurant or item id is invalid");
	    }

	    return itemQuantity;
	}

	@Override
	public ItemQuantity getQuantity(Long itemId, Long restaurantId) throws Exception {
	    ItemQuantity itemQuantity = null;

	    Optional<QuantityEntity> quantityEntityOptional = quantityRepository.findByItemIdAndRestaurantId(itemId, restaurantId);
	    ModelMapper modelMapper = modelMapperProvider.get();
	    if (quantityEntityOptional.isPresent()) {
	      QuantityEntity quantityEntity = quantityEntityOptional.get();

	      itemQuantity = modelMapper.map(quantityEntity, ItemQuantity.class);

	    } else {
	      throw new Exception("Either restaurant or item id is invalid");
	    }

	    return itemQuantity;
	}

    
}
