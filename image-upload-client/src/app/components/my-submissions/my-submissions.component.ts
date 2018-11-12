import { Component, OnInit } from '@angular/core';
import { User } from '../../model/model.user';
import { Submission } from '../../model/submission';
import { SubmissionService } from '../../services/submission.service';
import { AppComponent } from '../../app.component';

@Component({
  selector: 'app-my-submissions',
  templateUrl: './my-submissions.component.html',
  styleUrls: ['./my-submissions.component.css']
})
export class MySubmissionsComponent implements OnInit {

  APIUrl = AppComponent.API_URL;
  currentUser: User;
  submissions: Submission[] = [];

  constructor(public submissionService: SubmissionService) {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
  }

  ngOnInit() {
    this.submissionService.getSubmissions(this.currentUser)
      .subscribe(
        data => {
          this.submissions = data;
        },
        error => {
        });
  }
}
