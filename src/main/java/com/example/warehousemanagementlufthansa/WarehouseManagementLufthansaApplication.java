package com.example.warehousemanagementlufthansa;

import com.example.warehousemanagementlufthansa.domain.Role;
import com.example.warehousemanagementlufthansa.domain.User;
import com.example.warehousemanagementlufthansa.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class WarehouseManagementLufthansaApplication {

	public static void main(String[] args) {
		SpringApplication.run(WarehouseManagementLufthansaApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return  new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService){
		return args -> {
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_MANAGER"));

			userService.saveUser(new User(null, "user", "user","1234" , new ArrayList<>()));

			userService.addRoleToUser("user", "ROLE_USER");
			userService.addRoleToUser("user", "ROLE_MANAGER");

		};
	}
}

