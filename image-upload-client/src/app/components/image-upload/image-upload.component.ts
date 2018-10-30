import { HttpClient, HttpEventType } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
@Component({
  selector: 'image-upload',
  templateUrl: './image-upload.component.html',
  styleUrls: ['./image-upload.component.css']
})
export class ImageUploadComponent implements OnInit {

  url = '';
  progressValue: Number;
  private selectedFile: File;

  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.progressValue = 0;
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
    this.http.post('/api/uploadFile', uploadData, {
      reportProgress: true,
      observe: 'events'
    }).subscribe(event => {
      if (event.type === HttpEventType.UploadProgress) {
        console.log(event.total, event.loaded);
        this.progressValue = (event.loaded / event.total) * 100;
      }
      console.log(event);
    });
  }
}