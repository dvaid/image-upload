import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ImageUploadService {

  private upload: FormData;

  constructor() { }

  savePendingUpload(uploadData: FormData) {
    this.upload = uploadData;
  }

  getPendingUpload() {
    return this.upload;
  }
}
