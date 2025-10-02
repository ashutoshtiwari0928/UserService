package com.paypal.UserService.Service;

import com.paypal.UserService.DTO.LoginDTO;
import com.paypal.UserService.DTO.UserDTO;
import com.paypal.UserService.Entity.AuthUser;
import com.paypal.UserService.Repository.UserRepository;
import com.paypal.UserService.Util.JWTConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {
    public final UserRepository userRepository;
    public final BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTConfig jwtConfig;
    public String verify(LoginDTO login) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login.getEmail()
                        ,login.getPassword())
        );
        if(authentication.isAuthenticated()){
            Optional<AuthUser> user = userRepository.findByEmail(login.getEmail());
            if(user.isEmpty())throw new UsernameNotFoundException("User Not found");
            return jwtConfig.generate(new UserDTO(user.get().getName(),user.get().getEmail()));
        }
        else throw new UsernameNotFoundException("Invalid credentials");
    }
}
