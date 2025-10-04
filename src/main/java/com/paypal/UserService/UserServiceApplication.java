package com.paypal.UserService;

import com.paypal.UserService.Util.JWTConfig;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		ApplicationContext context =
				SpringApplication.run(UserServiceApplication.class, args);

	}

}
