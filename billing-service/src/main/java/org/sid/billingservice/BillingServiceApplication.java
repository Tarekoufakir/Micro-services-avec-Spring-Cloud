package org.sid.billingservice;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingServiceApplication.class, args);
	}

}

@Entity @Data @NoArgsConstructor @AllArgsConstructor class Bill{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; private Date billingDate;
	@OneToMany(mappedBy = "bill")
	private Collection<ProductItem> productItems;
	private long customerID;
	@Transient private Customer customer;
}
@RepositoryRestResource
interface BillRepository extends JpaRepository<Bill,Long>{}

@Entity @Data @NoArgsConstructor @AllArgsConstructor
class ProductItem{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private long productID;
	private double price; private double quantity;
	@ManyToOne
	private Bill bill;
	@Transient private Product product;
}
@RepositoryRestResource
interface ProductItemRepository extends
		JpaRepository<ProductItem,Long>{

	List<ProductItem> findByBillId(Long billID);

}


@FeignClient(name="customer-service")
interface CustomerServiceClient{
	@GetMapping("/customers/{id}?projection=fullCustomer")
	Customer findCustomerById(@PathVariable("id") Long id);
}
@FeignClient(name="inventory-service")
interface InventoryServiceClient{
	@GetMapping("/products/{id}?projection=fullProduct")
	Product findProductById(@PathVariable("id") Long id);
	@GetMapping("/products?projection=fullProduct")
	PagedModel<Product> findAll();
}