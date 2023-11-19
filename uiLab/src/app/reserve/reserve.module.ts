import { MatPaginatorModule } from '@angular/material/paginator';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {MatTableModule} from '@angular/material/table';
import { AllReservesComponent } from './all-reserves/all-reserves.component';
import { MyReservesComponent } from './my-reserves/my-reserves.component';



@NgModule({
  declarations: [
    AllReservesComponent,
    MyReservesComponent
  ],
  imports: [
    CommonModule,
    MatTableModule,
    MatPaginatorModule
  ],
  exports: [
  ]
})
export class ReserveModule { }
