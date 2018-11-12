import { Injectable } from '@angular/core';
import { Http } from "@angular/http";
import { ReplaySubject } from 'rxjs';
import { map } from 'rxjs/operators';
import { AppComponent } from "../app.component";
import { User } from "../model/model.user";


@Injectable()
export class AccountService {
  constructor(public http: Http) { }

  userRegisteredSubject = new ReplaySubject<User>(1);

  userRegistered(user: User) {
    this.userRegisteredSubject.next(user);
  }

  createAccount(user: User) {
    return this.http.post(AppComponent.API_URL + '/account/register', user).pipe(
      map(resp => resp.json()));
  }
}
