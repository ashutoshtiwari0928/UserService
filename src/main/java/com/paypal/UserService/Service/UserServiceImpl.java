package com.paypal.UserService.Service;

import com.paypal.UserService.DTO.UserDTO;
import com.paypal.UserService.Entity.AuthUser;
import com.paypal.UserService.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDTO createUser(AuthUser authUser) {
        authUser.password = passwordEncoder.encode(authUser.password);
        userRepository.save(authUser);
        return new UserDTO(authUser.name, authUser.email);
    }

    @Override
    public Optional<UserDTO> getUserById(Long id) {
        AuthUser u = userRepository.findById(id).orElse(null);
        if(u==null)return Optional.empty();
        return Optional.of(new UserDTO(u.getName(),u.getEmail()));
    }

    @Override
    public List<UserDTO> GetAllUsers() {
        List<AuthUser> list = userRepository.findAll();
        return list
                .stream()
                .map(u -> new UserDTO(u.getName(),u.getEmail()))
                .toList();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.delete(userRepository.getReferenceById(id));
    }

}
