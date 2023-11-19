import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Reserve } from './all-reserves/all-reserves.component';
import { AuthService } from '../security/auth.service';

@Injectable({
  providedIn: 'root'
})
export class ReserveService {

  url :string = 'http://localhost:8080/reserves'//'http://0.tcp.sa.ngrok.io:18120/reserves';
  token:any = localStorage.getItem('token');
  
  constructor(private http :HttpClient, private auth :AuthService) { }

  findAllReserves(pageSize: Number, pageIndex: Number): Observable<Reserve>{
    let url = `${this.url}?size=${pageSize}&page=${pageIndex}&sort=startTime,asc&reserveStatus=RESERVED`
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${this.token}`
    });
   return this.http.get<Reserve>(url, {headers});
  }

  findAllReservesByUser(pageSize: Number, pageIndex: Number): Observable<Reserve>{
    let token = this.auth.decodeToken(this.token);
    let url = `http://localhost:8080/instructors/${token.sub}/reserves?size=${pageSize}&page=${pageIndex}`;
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${this.token}`
    });
    return this.http.get<Reserve>(url, {headers});
  }
}
