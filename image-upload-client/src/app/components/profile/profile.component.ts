import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { User } from "../../model/model.user";
import { Submission } from '../../model/submission';
import { SubmissionService } from '../../services/submission.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
})
export class ProfileComponent implements OnInit {

  currentUser: User;
  submissions: Submission[];

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