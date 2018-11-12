import { HttpClient, HttpEventType } from '@angular/common/http';
import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { ImageUploadService } from '../../services/image-upload.service';
import { AccountService } from '../../services/account.service';
import { User } from '../../model/model.user';
@Component({
  selector: 'image-upload',
  templateUrl: './image-upload.component.html',
  styleUrls: ['./image-upload.component.css']
})
export class ImageUploadComponent implements OnInit {

  public isLoggedIn: boolean;
  url: string = '';
  progressValue: Number;
  public selectedFile: File;

  private loggedInUser: User = JSON.parse(localStorage.getItem('currentUser'));

  constructor(private http: HttpClient, private imageUploadService: ImageUploadService, private accountService: AccountService,
    private router: Router) { }

  ngOnInit() {
    this.progressValue = 0;
    if (this.loggedInUser) {
      this.isLoggedIn = true;
      // console.log(localStorage.getItem('currentUser'));
    }
  }
  onSelectFile(event) {
    this.progressValue = 0;
    if (event.target.files && event.target.files[0]) {
      const reader = new FileReader();
      this.selectedFile = event.target.files[0];
      reader.readAsDataURL(this.selectedFile); // read file as data url
      reader.onload = (uploadProgressEvent) => { // called once readAsDataURL is completed
        this.url = reader.result;
      };
    }
  }

  uploadFile() {
    const uploadData = new FormData();
    uploadData.append('file', this.selectedFile, this.selectedFile.name);
    this.imageUploadService.savePendingUpload(uploadData);
    if (this.isLoggedIn) {
      this.doUpload(this.loggedInUser);
    } else {
      this.router.navigate(['register']);
      // subscribe to user registered behavior subject
      this.accountService.userRegisteredSubject.subscribe(registeredUser => this.doUpload(registeredUser));
    }
  }

  private doUpload(user: User) {
    let uploadData = this.imageUploadService.getPendingUpload();
    console.log("uploading file: " + uploadData + " for user: " + user);
    this.http.post(`/api/uploadFile`, uploadData, {
      reportProgress: true,
      observe: 'events'
    }).subscribe(event => {
      if (event.type === HttpEventType.UploadProgress) {
        console.log(event.total, event.loaded);
        this.progressValue = (event.loaded / event.total) * 100;
        if (this.progressValue == 100) {
          this.router.navigate(['my-submissions']);
        }
        console.log(event);
      }
    });
  }
}
