import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'uiLab';

  constructor(private router:Router){}

  showMenu() {
    return this.router.url !== "/login";
  }
}
