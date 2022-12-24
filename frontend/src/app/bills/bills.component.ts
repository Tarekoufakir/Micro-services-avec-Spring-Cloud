import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-bills',
  templateUrl: './bills.component.html',
  styleUrls: ['./bills.component.css']
})
export class BillsComponent implements OnInit{
  
  customerID! : number;
  bills : any;
  constructor(private http: HttpClient, private router: Router, private route: ActivatedRoute) {
    this.customerID = route.snapshot.params['customerID'];
  }

  ngOnInit(): void {
    this.http.get(`http://localhost:8888/BILLING-SERVICE/bills/search/byCustomerID?customerID=${this.customerID}&projection=fullBill`)
      .subscribe({
      next : (data) => {
        this.bills = data;
      },
      error : (err) => {}
    })
  }

  getBillDetails(bill: any) {
    this.router.navigateByUrl(`/bill-details/${bill.id}`)
  }
}
