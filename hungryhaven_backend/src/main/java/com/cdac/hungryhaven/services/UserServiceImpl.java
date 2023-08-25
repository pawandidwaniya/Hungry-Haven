package com.cdac.hungryhaven.services;


import com.cdac.hungryhaven.dto.User;
import com.cdac.hungryhaven.exceptions.UserNotFoundException;
import com.cdac.hungryhaven.exchanges.UserResponse;
import com.cdac.hungryhaven.models.ItemEntity;
import com.cdac.hungryhaven.models.UserEntity;
import com.cdac.hungryhaven.repositories.UserRepository;
import com.cdac.hungryhaven.repositoryservices.UserRepositoryService;
import jakarta.inject.Provider;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    Provider<ModelMapper> modelMapperProvider;

    @Autowired
    UserRepositoryService userRepositoryService;

    @Autowired
    UserRepository userRepository;

    @Override
    public UserResponse login(String username, String password) {
        try {
            if (userRepositoryService.checkLogin(username, password)) {
                return new UserResponse(userRepositoryService.getUserRestaurant(username), 0);
            } else {
                return new UserResponse(null, new UserNotFoundException().getErrorType());
            }
        } catch (UserNotFoundException e) {
            return new UserResponse(null, new UserNotFoundException().getErrorType());
        }
    }

    @Override
    public UserResponse register(User user) {
        ModelMapper modelMapper = modelMapperProvider.get();
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);


        try {
            UserEntity savedUser = userRepository.save(userEntity);
            return new UserResponse(savedUser.getId(), 0); // Return the new user's ID as a response
        } catch (Exception e) {
            // Handle registration failure, return appropriate response
            return new UserResponse(null, -1); // You can define custom error codes as needed
        }
    }
}


