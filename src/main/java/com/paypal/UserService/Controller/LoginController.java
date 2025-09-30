package com.paypal.UserService.Controller;

import com.paypal.UserService.DTO.LoginDTO;
import com.paypal.UserService.DTO.UserDTO;
import com.paypal.UserService.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;
    @PostMapping
    public ResponseEntity<?> loginRequest(@RequestBody LoginDTO login){
        try {
            System.out.println(login.getEmail()+" "+login.getPassword());
            return ResponseEntity.ok(loginService.verify(login));
        }
        catch (UsernameNotFoundException e){
            return ResponseEntity.badRequest().build();
        }
    }
}
