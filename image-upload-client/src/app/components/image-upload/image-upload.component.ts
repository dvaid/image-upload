import { HttpClient, HttpEventType } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AppComponent } from '../../app.component';
import { User } from '../../model/model.user';
import { AccountService } from '../../services/account.service';
import { AuthService } from '../../services/auth.service';
import { ImageUploadService } from '../../services/image-upload.service';
@Component({
  selector: 'image-upload',
  templateUrl: './image-upload.component.html',
  styleUrls: ['./image-upload.component.css']
})
export class ImageUploadComponent implements OnInit {

  url: string = '';
  brief: String = '';
  progressValue: Number;
  public selectedFile: File;
  selectedFileImg: HTMLImageElement;

  constructor(private http: HttpClient, private imageUploadService: ImageUploadService, private accountService: AccountService,
    private router: Router, public authService: AuthService,
    private route: ActivatedRoute) { }

  ngOnInit() {
    this.progressValue = 0;
    //3. if payment successful, emit event user-registered
    if (this.route.snapshot.queryParams['payment'] === "success") {
      // this.accountService.userRegistered(this.authService.user());
      this.doUpload(this.authService.user(), this.imageUploadService.getPendingUpload());
    }
  }

  onSelectFile(event) {
    var self = this;
    this.imageUploadService.compress(event, self._updateSelectedFile, self);
  }

  private _updateSelectedFile(compressedFile, self) {
    console.log(compressedFile.size);
    self.selectedFile = compressedFile;
  }

  uploadFile() {
    let uploadData = new FormData();
    uploadData.append('brief', this.brief.toString());
    // uploadData.append('file', this.selectedFile, this.selectedFile.name);
    uploadData.append('file', this.selectedFile, this.selectedFile.name);
    if (this.authService.IsUserLoggedin()) {
      this.doUpload(this.authService.user(), uploadData);
    } else {
      this.imageUploadService.savePendingUpload(this.selectedFile.name, uploadData);
      this.router.navigate(['register']);
      // subscribe to user registered behavior subject
      // this.accountService.userRegisteredSubject.subscribe(registeredUser => this.doUpload(registeredUser));
    }
  }

  private doUpload(user: User, uploadData: FormData) {
    // let uploadData = this.imageUploadService.getPendingUpload();
    uploadData.append('author', user.username);
    console.log("uploading file: " + uploadData + " for user: " + user);
    this.http.post(AppComponent.API_URL + `/uploadFile`, uploadData, {
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
