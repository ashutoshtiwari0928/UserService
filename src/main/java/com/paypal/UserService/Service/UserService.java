package com.paypal.UserService.Service;

import com.paypal.UserService.DTO.UserDTO;
import com.paypal.UserService.Entity.AuthUser;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDTO createUser(AuthUser authUser);

    Optional<UserDTO> getUserById(Long id);

    List<UserDTO> GetAllUsers();

    void deleteUser(Long id);

}
