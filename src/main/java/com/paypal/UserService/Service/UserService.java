package com.paypal.UserService.Service;

import com.paypal.UserService.Entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(User user);

    Optional<User> getUserById(Long id);

    List<User> GetAllUsers();

}
