import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-order-details',
  templateUrl: './order-details.component.html',
  styleUrls: ['./order-details.component.css']
})
export class OrderDetailsComponent implements OnInit{
  bills:any;

  orderId:any;

  constructor(private http:HttpClient,private router:Router,private activatedRoute:ActivatedRoute) {
    this.orderId=activatedRoute.snapshot.params['orderId']
  }

  ngOnInit(): void {
    this.http.get("http://localhost:8888/BILLING-SERVICE//fullBill/"+this.orderId).subscribe({
      next :(data)=>{
        this.bills=data;
      },
      error:(err)=>{}
    })
  }

}
