import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ToolBarComponent } from './tool-bar/tool-bar.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { AppRoutingModule } from '../app-routing.module';

@NgModule({
  declarations: [
    ToolBarComponent
  ],
  imports: [
    CommonModule,
    MatToolbarModule,
    MatIconModule,
    AppRoutingModule
  ],
  exports: [
    ToolBarComponent
  ]
})
export class MenuModule { }
