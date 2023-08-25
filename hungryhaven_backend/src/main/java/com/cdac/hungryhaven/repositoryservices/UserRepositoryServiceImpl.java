package com.cdac.hungryhaven.repositoryservices;

import com.cdac.hungryhaven.exceptions.UserNotFoundException;
import com.cdac.hungryhaven.models.UserEntity;
import com.cdac.hungryhaven.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRepositoryServiceImpl implements UserRepositoryService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean checkLogin(String username, String password) {
        Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(username);

        return optionalUserEntity
                .map(userEntity -> userEntity.getPassword().equals(password))
                .orElse(false);
    }

    @Override
    public Long getUserRestaurant(String username) throws UserNotFoundException {
        Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(username);

        return optionalUserEntity
                .map(UserEntity::getRestaurantId)
                .orElseThrow(UserNotFoundException::new);
    }
}

