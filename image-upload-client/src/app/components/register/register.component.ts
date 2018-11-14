import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { AppComponent } from "../../app.component";
import { PaymentDataModel } from "../../model/model.paymentdata";
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
  paymentData: PaymentDataModel = new PaymentDataModel();
  paymentUrl: string = AppComponent.PAYMENT_URL;
  isRegistered: boolean = false;

  constructor(public accountService: AccountService, public authService: AuthService,
    private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.accountService.getPaymentData().subscribe((paymentData: PaymentDataModel) => {
      this.paymentData = paymentData;
    });
  }

  register() {
    //1.register
    this.accountService.createAccount(this.user).subscribe(registeredUser => {
      //2.login
      this.authService.logIn(this.user)
        .subscribe(data => {
          this.isRegistered = true;
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
