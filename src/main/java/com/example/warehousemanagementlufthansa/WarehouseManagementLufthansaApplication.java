package com.example.warehousemanagementlufthansa;

import com.example.warehousemanagementlufthansa.domain.Role;
import com.example.warehousemanagementlufthansa.domain.User;
import com.example.warehousemanagementlufthansa.model.InventoryBody;
import com.example.warehousemanagementlufthansa.model.InventoryHeader;
import com.example.warehousemanagementlufthansa.model.Item;
import com.example.warehousemanagementlufthansa.model.OrderStatus;
import com.example.warehousemanagementlufthansa.service.ItemService;
import com.example.warehousemanagementlufthansa.service.OrderService;
import com.example.warehousemanagementlufthansa.service.UserService;
import com.example.warehousemanagementlufthansa.specification.Spec;
import org.aspectj.weaver.ast.Or;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;

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
	CommandLineRunner run(UserService userService , ItemService itemService , OrderService inventoryHeader){
		return args -> {
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_MANAGER"));

			userService.saveUser(new User(null, "user", "user","1234" , new ArrayList<>()));

			userService.addRoleToUser("user", "ROLE_USER");
//			userService.addRoleToUser("user", "ROLE_MANAGER");

			itemService.save(new Item(null , "item1", BigDecimal.TEN, "unit" ));
			itemService.save(new Item(null , "coffee", BigDecimal.valueOf(233), "gr" ));
			itemService.save(new Item(null , "water", BigDecimal.valueOf(4555), "l" ));
			itemService.save(new Item(null , "orange juice", BigDecimal.valueOf(5637), "ml" ));
			itemService.getAllItems();
			User user = userService.getUser("user");

			List<InventoryBody> inventoryBodyList = new ArrayList<>();
			inventoryBodyList.add( new InventoryBody(null ,Long.valueOf(4), "orange juice", "ml" , BigDecimal.valueOf(5637)));
			inventoryBodyList.add( new InventoryBody(null ,Long.valueOf(5), "coffee","gr", BigDecimal.valueOf(230) ));

			inventoryHeader.save(new InventoryHeader(null, user , OrderStatus.APPROVED ,inventoryBodyList));
			inventoryHeader.save(new InventoryHeader(null, user , OrderStatus.CANCELLED ,Collections.emptyList()));
			inventoryHeader.save(new InventoryHeader(null, user , OrderStatus.AWAITING_APPROVAL ,Collections.emptyList()));
			inventoryHeader.save(new InventoryHeader(null, user , OrderStatus.UNDER_DELIVERY ,Collections.emptyList()));

			inventoryHeader.getAll();
			itemService.getAllItems();

//			InventoryHeader inventoryHeader1 = new InventoryHeader(null, user , OrderStatus.AWAITING_APPROVAL , Collections.emptyList());
//			inventoryHeader.update(Long.valueOf(8), inventoryHeader1);
//			inventoryHeader.getAll();
//			inventoryHeader.cancel(Long.valueOf(8));
//			inventoryHeader.findAll(Spec.searchByWarehouse(OrderStatus.APPROVED.toString()));


		};
	}
}

