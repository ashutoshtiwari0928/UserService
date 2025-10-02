package com.paypal.UserService;

import com.paypal.UserService.Util.JWTConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		ApplicationContext context =
				SpringApplication.run(UserServiceApplication.class, args);

	}

}
