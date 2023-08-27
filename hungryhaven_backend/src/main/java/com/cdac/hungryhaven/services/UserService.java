package com.cdac.hungryhaven.services;


import com.cdac.hungryhaven.dto.User;
import com.cdac.hungryhaven.exchanges.UserResponse;

public interface UserService {

    UserResponse login(String username, String password);

    UserResponse register(User user);
}
