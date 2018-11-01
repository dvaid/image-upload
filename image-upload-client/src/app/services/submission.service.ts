import { Injectable } from '@angular/core';
import { User } from '../model/model.user';
import { Submission } from '../model/submission';
import { AppComponent } from '../app.component';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class SubmissionService {

  constructor(public http: Http) { }

  getSubmissions(user: User): Observable<Submission[]> {
    return this.http.get(AppComponent.API_URL + "/" + user.username + "/submissions", {}).pipe(
      map((response: Response) => {
        const submissions: Submission[] = [];
        response.json().map(submission => {
          submissions.push(submission);
        });
        return submissions;
      }));
  }
}
