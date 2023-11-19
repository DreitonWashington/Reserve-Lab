import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AllReservesComponent } from './reserve/all-reserves/all-reserves.component';
import { RouterModule, Routes } from '@angular/router';
import { AllLabsComponent } from './lab/all-labs/all-labs.component';
import { LoginComponent } from './security/login/login.component';
import { Guard } from './security/guard.guard';
import { MyReservesComponent } from './reserve/my-reserves/my-reserves.component';


const routes :Routes = [
  {path: 'reserves', component: AllReservesComponent, canActivate: [Guard]},
  {path: 'my-reserves', component: MyReservesComponent, canActivate: [Guard]},
  {path: 'labs', component: AllLabsComponent, canActivate: [Guard]},
  {path: 'login', component: LoginComponent},
  {path: '', redirectTo: 'login', pathMatch: 'full'}
]

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule {}
