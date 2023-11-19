import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Lab } from './all-labs/all-labs.component';

@Injectable({
  providedIn: 'root'
})
export class LabService {

  url :string = 'http://localhost:8080/labs';
  token:any = localStorage.getItem('token');

  constructor(private http :HttpClient) { }

  findAllLabs(pageSize: Number, pageIndex: Number): Observable<Lab>{
    let url = `${this.url}?size=${pageSize}&page=${pageIndex}`
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${this.token}`
    });
   return this.http.get<Lab>(url, {headers});
  }
}
