import { HttpClient } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';

import { JwtHelperService } from '@auth0/angular-jwt';

const helper = new JwtHelperService();

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  login(username:String, password:String){

    const url = 'http://localhost:8080/auth/login';

    const body = {
      "username": `${username}`,
      "password": `${password}`
    }
  
    return this.http.post(url, body).toPromise().then((response:any) => this.saveToken(response['token']))
  }

  saveToken(token: string){
    localStorage.setItem('token',token);
  }

  getToken(){
    return localStorage.getItem('token');
  }

  isTokenExpired(token: string){
    return helper.isTokenExpired(token);
  }

  decodeToken(token: string){
    return helper.decodeToken(token);
  }
  

}
