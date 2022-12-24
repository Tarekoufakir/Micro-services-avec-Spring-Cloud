package org.sid.billingservice;

import lombok.AllArgsConstructor;import lombok.Data; import lombok.NoArgsConstructor;
import org.sid.billingservice.entities.Bill;
import org.sid.billingservice.entities.ProductItem;
import org.sid.billingservice.feign.CustomerServiceClient;
import org.sid.billingservice.feign.InventoryServiceClient;
import org.sid.billingservice.model.Customer;
import org.sid.billingservice.model.Product;
import org.sid.billingservice.repository.BillRepository;
import org.sid.billingservice.repository.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(BillingServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(BillRepository billRepository, ProductItemRepository productItemRepository,
							CustomerServiceClient customerServiceClient, InventoryServiceClient inventoryServiceClient) {
		return args -> {
			Customer customer=customerServiceClient.getCustomerById(1L);
			System.out.println(".......");
			System.out.println(customer.getId());
			System.out.println(customer.getName());
			System.out.println(customer.getEmail());
		};
	}
}