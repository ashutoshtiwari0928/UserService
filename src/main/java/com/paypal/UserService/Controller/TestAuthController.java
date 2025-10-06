package com.paypal.UserService.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Tag(name = "Test Authentication using JWT")
public class TestAuthController {
    @Operation(summary = "Test JWT authentication using bearer token.")
    @GetMapping
    public ResponseEntity<?> respond(){
        return ResponseEntity.ok("Working");
    }
}
