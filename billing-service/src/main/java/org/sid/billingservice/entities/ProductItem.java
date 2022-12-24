package org.sid.billingservice.entities;

import lombok.*;
import org.sid.billingservice.model.Product;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor @ToString
@Builder
public
class ProductItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long productID;
    private double price;
    private double quantity;
    @ManyToOne
    private Bill bill;
    @Transient
    private Product product;

    @Transient
    private String productName;



}
