import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Router } from "@angular/router";
import { User } from "../../model/model.user";
import { AccountService } from "../../services/account.service";
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class RegisterComponent implements OnInit {
  user: User = new User();
  errorMessage: string;

  constructor(public accountService: AccountService, public authService: AuthService, public router: Router) {
  }

  ngOnInit() {
  }

  register() {
    //1.register
    this.accountService.createAccount(this.user).subscribe(data => {
      //2.login
      this.authService.logIn(this.user)
        .subscribe(data => {
          //3.emit event user-registered
          this.accountService.userRegistered(this.user);
          //4.redirect to payments
          this.router.navigate(['/login']);
        }, err => {
          this.errorMessage = "error :  Username or password is incorrect";
        });
      // this.router.navigate(['/login']);
    }, err => {
      console.log(err);
      this.errorMessage = "username already exist";
    }
    )
  }
}
