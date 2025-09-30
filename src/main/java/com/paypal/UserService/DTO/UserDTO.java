package com.paypal.UserService.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
public class UserDTO {
    private String name;
    private String email;
}
