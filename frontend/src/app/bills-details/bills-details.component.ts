import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-bills-details',
  templateUrl: './bills-details.component.html',
  styleUrls: ['./bills-details.component.css']
})
export class BillsDetailsComponent implements OnInit {

  billDetails : any;
  billID! : number;
  constructor(private http: HttpClient, private router: Router, private route: ActivatedRoute) {
    this.billID = route.snapshot.params['billID'];
  }

  ngOnInit(): void {
    this.http.get(`http://localhost:8888/BILLING-SERVICE/fullBill/${this.billID}`).subscribe({
      next : (data)=>{
        this.billDetails = data;
      },
      error : (err)=>{

      }
    })
  }

}
