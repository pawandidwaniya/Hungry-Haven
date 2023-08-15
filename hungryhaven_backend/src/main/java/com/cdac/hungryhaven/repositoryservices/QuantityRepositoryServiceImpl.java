package com.cdac.hungryhaven.repositoryservices;

import com.cdac.hungryhaven.dto.Quantity;
import com.cdac.hungryhaven.models.QuantityEntity;
import com.cdac.hungryhaven.repositories.QuantityRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuantityRepositoryServiceImpl implements QuantityRepositoryService {

    private final QuantityRepository quantityRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public QuantityRepositoryServiceImpl(QuantityRepository quantityRepository, ModelMapper modelMapper) {
        this.quantityRepository = quantityRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Quantity updateQuantity(Long itemId, Long restaurantId, int quantity) {
        Quantity itemQuantity = null;
        Optional<QuantityEntity> quantityEntityOptional = quantityRepository.findByItemIdAndRestaurantId(itemId, restaurantId);
        if (quantityEntityOptional.isPresent()) {
            QuantityEntity quantityEntity = quantityEntityOptional.get();
            quantityEntity.setQuantity(quantity);
            quantityRepository.save(quantityEntity);

            itemQuantity = modelMapper.map(quantityEntity, Quantity.class);

        } else {
            throw new RuntimeException("Either restaurant or item id is invalid");
        }

        return itemQuantity;
    }

    @Override
    public Quantity getQuantity(Long itemId, Long restaurantId) throws Exception {
        Quantity itemQuantity = null;

        Optional<QuantityEntity> quantityEntityOptional = quantityRepository.findByItemIdAndRestaurantId(itemId, restaurantId);
        if (quantityEntityOptional.isPresent()) {
            QuantityEntity quantityEntity = quantityEntityOptional.get();

            itemQuantity = modelMapper.map(quantityEntity, Quantity.class);

        } else {
            throw new Exception("Either restaurant or item id is invalid");
        }

        return itemQuantity;
    }
}
