package com.cdac.hungryhaven.repositoryservices;

import com.cdac.hungryhaven.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface UserRepositoryService {

    boolean checkLogin(String username, String password);

    Long getUserRestaurant(String username) throws UserNotFoundException;
}
