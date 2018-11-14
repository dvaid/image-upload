import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ImageUploadService {

  private filename: String = "";
  constructor() { }

  savePendingUpload(filename, uploadData: FormData) {
    this.filename = filename;
    localStorage.setItem('brief', uploadData.get('brief').toString());
    this.fileToBase64(uploadData.get('file'));
  }

  private base64ToFile(dataurl, filename): File {
    var arr = dataurl.split(','), mime = arr[0].match(/:(.*?);/)[1],
      bstr = atob(arr[1]), n = bstr.length, u8arr = new Uint8Array(n);
    while (n--) {
      u8arr[n] = bstr.charCodeAt(n);
    }
    return new File([u8arr], filename, { type: mime });
  }

  private fileToBase64(file) {
    var reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = function () {
      console.log(reader.result);
      localStorage.setItem('file', reader.result.toString());
    };
    reader.onerror = function (error) {
      console.log('Error: ', error);
      return error;
    };
  }


  getPendingUpload(): FormData {
    let uploadData = new FormData();
    uploadData.append('brief', localStorage.getItem('brief'));
    uploadData.append('file', this.base64ToFile(localStorage.getItem('file'), this.filename));
    return uploadData;
  }
}
