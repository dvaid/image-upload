import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-side-nav',
  templateUrl: './side-nav.component.html',
  styleUrls: ['./side-nav.component.css']
})
export class SideNavComponent implements OnInit {

  private isLoggedIn:boolean=false;

  constructor(public authService: AuthService,
              public router: Router) { }

  ngOnInit() {
    if(localStorage.getItem('currentUser')){
      this.isLoggedIn = localStorage.getItem('currentUser').length>0;
      console.log( localStorage.getItem('currentUser'));
    }
  }

  logOut() {
    console.log('logout');
    this.authService.logOut()
      .subscribe(
        data => {
          localStorage.clear;
          window.location.reload();
          this.router.navigate(['/home']);
        },
        error => {

        });
  }

}
