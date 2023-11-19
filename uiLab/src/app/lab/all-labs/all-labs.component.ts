import { PaginatorIntlService } from 'src/app/reserve/paginator-intl.service';
import { LabService } from './../lab.service';
import { Component, OnInit } from '@angular/core';
import { MatPaginatorIntl, PageEvent } from '@angular/material/paginator';

export interface Lab {
  labId :string,
  block :string,
  name :string,
  capacity :number
}

@Component({
  selector: 'app-all-labs',
  templateUrl: './all-labs.component.html',
  styleUrls: ['./all-labs.component.css'],
  providers: [{provide: MatPaginatorIntl, useClass: PaginatorIntlService}]
})
export class AllLabsComponent implements OnInit{
  constructor(private labService: LabService) {}

  labs:any[] = [];
  dataSource = this.labs;
  displayedColumns: string[] = ['labId', 'block', 'name', 'capacity'];

  ngOnInit(): void {
    this.findAllLabs();
  }

  findAllLabs(){
    this.labService.findAllLabs(this.pageSize, this.currentPage).subscribe((data) => {
      this.labs = data['content']
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
    this.labService.findAllLabs(this.pageSize, this.currentPage).subscribe((data) => {
      this.labs = data['content']
    })
  }


  
}
