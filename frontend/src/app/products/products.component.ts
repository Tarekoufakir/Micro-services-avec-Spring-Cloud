import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
  
  products: any;

  constructor(private http:HttpClient) {}
  
  ngOnInit(): void {
    /* Making a call to the product service to get the list of products. */
    this.http.get("http://localhost:8888/PRODUCT-SERVICE/products?projection=fullProduct").subscribe({
      next : (data) => {
        this.products = data;
      },
      error : (err) => {}
    });
  }
}
