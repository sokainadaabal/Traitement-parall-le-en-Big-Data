import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit{
  bills:any;

  customersId:any;

  constructor(private http:HttpClient,private router:Router,private activatedRoute:ActivatedRoute) {
    this.customersId=activatedRoute.snapshot.params['customerId']
  }

  ngOnInit(): void {
    this.http.get("http://localhost:8888/BILLING-SERVICE/bills/search/byCustomerId?customerId="+this.customersId).subscribe({
      next :(data)=>{
        this.bills=data;
      },
      error:(err)=>{}
    })
  }

  getOrderDetails(b:any) {
    this.router.navigateByUrl("/order-details/"+b.id);
  }
}
