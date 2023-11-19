import { Component, OnInit } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';
import { ReserveService } from '../reserve.service';

@Component({
  selector: 'app-my-reserves',
  templateUrl: './my-reserves.component.html',
  styleUrls: ['./my-reserves.component.css']
})
export class MyReservesComponent implements OnInit{

  constructor(private reserveService :ReserveService) {}
  
  ngOnInit(): void {
    this.findAllReservesFromUser();
  }
  
  myReserves:any[] = [];
  dataSource = this.myReserves;
  displayedColumns: string[] = ['id', 'block', 'lab', 'subject', 'reserve_start', 'reserve_end', 'instructor', 'status'];
  

  showBtMsg(){
    let element = document.getElementById('bp-web-widget-container');
    if(window.location.pathname !== '/login')
      element?.classList.remove('hidden')
  }

  findAllReservesFromUser(){
    this.reserveService.findAllReservesByUser(this.pageSize, this.currentPage).subscribe((data) => {
      this.myReserves = data['content']
      this.length = data['totalElements']
    })
  }

  currentPage:any = 0;
  pageSize: Number = 5;
  length: any;
  page: PageEvent;

  handlePage(event:PageEvent){
    this.pageSize = event.pageSize
    this.currentPage = event.pageIndex
    this.reserveService.findAllReservesByUser(this.pageSize, this.currentPage).subscribe((data) => {
      this.myReserves = data['content']
    })
  }
}
