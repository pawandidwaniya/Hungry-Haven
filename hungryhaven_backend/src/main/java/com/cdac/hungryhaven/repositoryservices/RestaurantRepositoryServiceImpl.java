package com.cdac.hungryhaven.repositoryservices;


import com.cdac.hungryhaven.dto.Restaurant;
import com.cdac.hungryhaven.exceptions.RestaurantNotFoundException;
import com.cdac.hungryhaven.models.RestaurantEntity;
import com.cdac.hungryhaven.repositories.RestaurantRepository;
import jakarta.inject.Provider;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestaurantRepositoryServiceImpl implements RestaurantRepositoryService {

    @Autowired
    private final RestaurantRepository restaurantRepository;


    @Autowired
    Provider<ModelMapper> modelMapperProvider;

    @Autowired
    public RestaurantRepositoryServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Restaurant createRestaurant(Restaurant restaurant) {
        ModelMapper modelMapper = modelMapperProvider.get();
        RestaurantEntity restaurantEntity = modelMapper.map(restaurant, RestaurantEntity.class);
        restaurantEntity = restaurantRepository.save(restaurantEntity);
        return modelMapper.map(restaurantEntity, Restaurant.class);
    }

    @Override
    public Restaurant getRestaurantById(Long id) {
        ModelMapper modelMapper = modelMapperProvider.get();

        Optional<RestaurantEntity> optionalRestaurantEntity = restaurantRepository.findById(id);

        Optional<Restaurant> optionalRestaurant = Optional.empty();

        Restaurant restaurant = null;

        if (optionalRestaurantEntity.isPresent()) {
            restaurant = modelMapper.map(optionalRestaurantEntity.get(), Restaurant.class);
            //optionalRestaurant = Optional.of(restaurant);
        } else {
            throw new RestaurantNotFoundException("Restaurant not found with id: " + id);
        }
        return restaurant;
    }

    public Restaurant updateRestaurant(Long id, Restaurant restaurant) {
        Optional<RestaurantEntity> optionalRestaurantEntity = restaurantRepository.findById(id);

        if (optionalRestaurantEntity.isPresent()) {
            RestaurantEntity restaurantEntity = optionalRestaurantEntity.get();
            ModelMapper modelMapper = modelMapperProvider.get();

            modelMapper.map(restaurant, restaurantEntity);
            restaurantEntity = restaurantRepository.save(restaurantEntity);

            return modelMapper.map(restaurantEntity, Restaurant.class);
        } else {
            throw new RestaurantNotFoundException("Restaurant not found with id: " + id);
        }
    }


}

