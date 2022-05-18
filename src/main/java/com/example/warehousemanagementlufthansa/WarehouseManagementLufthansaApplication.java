package com.example.warehousemanagementlufthansa;

import com.example.warehousemanagementlufthansa.domain.Role;
import com.example.warehousemanagementlufthansa.domain.User;
import com.example.warehousemanagementlufthansa.model.Item;
import com.example.warehousemanagementlufthansa.service.ItemService;
import com.example.warehousemanagementlufthansa.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
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
	CommandLineRunner run(UserService userService , ItemService itemService){
		return args -> {
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_MANAGER"));

			userService.saveUser(new User(null, "user", "user","1234" , new ArrayList<>()));

			userService.addRoleToUser("user", "ROLE_USER");
			userService.addRoleToUser("user", "ROLE_MANAGER");

			itemService.save(new Item(null , "item1", BigDecimal.TEN, "unit" ));
			itemService.save(new Item(null , "coffee", BigDecimal.valueOf(233), "gr" ));
			itemService.save(new Item(null , "water", BigDecimal.valueOf(4555), "l" ));
			itemService.save(new Item(null , "orange juice", BigDecimal.valueOf(5637), "ml" ));
			itemService.getAllItems();



		};
	}
}

