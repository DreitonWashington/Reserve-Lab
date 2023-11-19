import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{

  form:FormGroup;

  constructor(private formBuilder: FormBuilder, private auth: AuthService, private router : Router) {
    this.form = this.formBuilder.group({
      username: '',
      password: ''
    })
  }
  ngOnInit(): void {
    this.hiddenBtMsg();
  }

  login(){
    this.auth.login(this.form.get('username')?.value, this.form.get('password')?.value).then(() => {
      this.router.navigate(['/reserves'])
    }).catch(() => this.msg = 'Username or password wrong!')
  }

  msg:string;

  hiddenBtMsg(){
    let element = document.getElementById('bp-web-widget-container');
    if(window.location.pathname === '/login' || window.location.pathname === '/')
      element?.classList.add('hidden')
  }
}
