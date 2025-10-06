package com.paypal.UserService.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.aspectj.lang.annotation.RequiredTypes;

@Entity
@Table(name="app_user")
@Data
@NoArgsConstructor
public class AuthUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotNull
    private  String name;
    @Column(unique = true)
    @NotNull
    private  String email;
    @NotNull
    private String password;
    @Enumerated(EnumType.STRING)
    @NotNull
    private role role;

    public AuthUser(String name, String email,String password,  role role) {
        this.password = password;
        this.name = name;
        this.email = email;
        this.role = role;
    }
}
