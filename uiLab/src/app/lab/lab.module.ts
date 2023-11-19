import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AllLabsComponent } from './all-labs/all-labs.component';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';



@NgModule({
  declarations: [
    AllLabsComponent
  ],
  imports: [
    CommonModule,
    MatTableModule,
    MatPaginatorModule
  ]
})
export class LabModule { }
