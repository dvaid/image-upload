import { Injectable } from '@angular/core';
import { Http } from "@angular/http";
import { ReplaySubject } from 'rxjs';
import { map } from 'rxjs/operators';
import { AppComponent } from "../app.component";
import { User } from "../model/model.user";
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';


@Injectable()
export class AccountService {
    private headers = new Headers();

    constructor(public http: Http,
                public httpClient: HttpClient) {
    }

  userRegisteredSubject = new ReplaySubject<User>(1);

  userRegistered(user: User) {
    this.userRegisteredSubject.next(user);
  }

  createAccount(user: User) {
    return this.http.post(AppComponent.API_URL + '/account/register', user).pipe(
      map(resp => resp.json()));
  }


    getPaymentData<PaymentDataModel>() {
        return this.httpClient.get(AppComponent.API_URL + '/payment');
    }
}
