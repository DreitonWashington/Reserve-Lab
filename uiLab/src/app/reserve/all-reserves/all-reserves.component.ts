import { Component, OnInit } from '@angular/core';
import { ReserveService } from '../reserve.service';
import { MatPaginatorIntl, PageEvent } from '@angular/material/paginator';
import { PaginatorIntlService } from '../paginator-intl.service';

export interface Reserve {
  id: string
  block: string
  lab: string;
  subject: string
  reserve_start: string;
  reserve_end: string;
  instructor: string;
  status: string
}

@Component({
  selector: 'app-all-reserves',
  templateUrl: './all-reserves.component.html',
  styleUrls: ['./all-reserves.component.css'],
  providers: [{provide: MatPaginatorIntl, useClass: PaginatorIntlService}]
})
export class AllReservesComponent implements OnInit{

  constructor(private reserveService :ReserveService) {}
  
  ngOnInit(){
    this.showBtMsg()
    this.findAllReserves()
  }

  reserves:any[] = [];
  dataSource = this.reserves;
  displayedColumns: string[] = ['id', 'block', 'lab', 'subject', 'reserve_start', 'reserve_end', 'instructor', 'status'];
  

  // findAllReserves(){
  //   this.reserveService.findAllReserves(this.pageSize, this.currentPage).then((response :any) => console.log(response));
  // }

  showBtMsg(){
    let element = document.getElementById('bp-web-widget-container');
    if(window.location.pathname !== '/login')
      element?.classList.remove('hidden')
  }

  findAllReserves(){
    this.reserveService.findAllReserves(this.pageSize, this.currentPage).subscribe((data) => {
      this.reserves = data['content'];
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
    this.reserveService.findAllReserves(this.pageSize, this.currentPage).subscribe((data) => {
      this.reserves = data['content']
    })
  }
}
