import { Injectable } from '@angular/core';
import { User } from '../model/model.user';
import { Submission } from '../model/submission';
import { AppComponent } from '../app.component';
import { Response } from '@angular/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SubmissionService {

  constructor(public http: HttpClient) { }

  getSubmissions(user: User): Observable<Submission[]> {
    return this.http.get(AppComponent.API_URL + "/" + user.username + "/submissions", {}).pipe(
      map((response: Response) => {
        const submissions: Submission[] = [];
        // response.map(submission => {
        //   submissions.push(submission);
        // });
        return submissions;
      }));
  }
}
