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
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
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
        Optional<AuthUser> authUser= userRepository.findByEmail(login.getEmail());
        if(authUser.isEmpty())throw new UsernameNotFoundException("Invalid credentials");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.getEmail(),
                        login.getPassword(),
                        List.of(
                                new SimpleGrantedAuthority(authUser.get().getRole().toString())
                        )
                )
        );
        if(authentication.isAuthenticated()){
            return jwtConfig.generate(
                    new UserDTO(authUser.get().getName(),
                    authUser.get().getEmail()),
                    authUser.get().getRole()
            );
        }
        else throw new UsernameNotFoundException("Invalid credentials");
    }
}
