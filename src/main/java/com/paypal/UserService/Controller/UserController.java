package com.paypal.UserService.Controller;

import com.paypal.UserService.DTO.AuthDTO;
import com.paypal.UserService.DTO.UserDTO;
import com.paypal.UserService.Entity.AuthUser;
import com.paypal.UserService.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User CRUD APIs", description = "APIs to create user, get users by id, see all users, see users, by id, and delete users by id.")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @Operation(summary = "Sign up.",description = "public API.")
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody AuthDTO authDTO){
        UserDTO userDTO= userService.createUser(authDTO);
        return new ResponseEntity<>(userDTO,HttpStatus.CREATED);
    }
    @Operation(summary = "Get users by id.", description = "Admin authenticated.")
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
        return userService
                .getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @Operation(summary = "Get all users.",description = "Admin authenticated.")
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(userService.GetAllUsers());
    }

    @Operation(summary = "Delete user by id.",description = "Admin authenticated.")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

}
