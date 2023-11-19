import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/security/auth.service';

@Component({
  selector: 'app-tool-bar',
  templateUrl: './tool-bar.component.html',
  styleUrls: ['./tool-bar.component.css']
})
export class ToolBarComponent implements OnInit{
  
  constructor(private router: Router, private auth: AuthService) {}
  username:string;
  ngOnInit(): void {
    this.getUsername();
  }

  logout(){
    localStorage.removeItem('token')
    this.router.navigate(['/login']);
  }

  getUsername(){
    let user:string = this.auth.decodeToken(localStorage.getItem('token')).username
    let upper = user.charAt(0).toUpperCase()
    return this.username = user.replace(user.charAt(0), upper)
  }
}
