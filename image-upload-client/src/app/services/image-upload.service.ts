import { Injectable } from '@angular/core';
import { AppComponent } from '../app.component';

@Injectable({
  providedIn: 'root'
})
export class ImageUploadService {

  constructor() { }

  savePendingUpload(filename, uploadData: FormData) {
    localStorage.setItem('brief', uploadData.get('brief').toString());
    localStorage.setItem('filename', filename.toString());
    let originalFile: File = <File>uploadData.get('file');
    this.fileToBase64(originalFile);
  }

  compress(e, _callback, self) {
    const width = 500;
    const height = 300;
    const fileName = e.target.files[0].name;
    const reader = new FileReader();
    reader.readAsDataURL(e.target.files[0]);
    reader.onload = event => {
      const img = new Image();
      img.src = (<FileReaderProgressEvent>event).target.result;
      img.onload = () => {
        const elem = document.createElement('canvas');
        elem.width = width;
        elem.height = height;
        const ctx = elem.getContext('2d');
        // img.width and img.height will give the original dimensions
        ctx.drawImage(img, 0, 0, width, height);
        ctx.canvas.toBlob((blob) => {
          console.log(blob.size);
          const compressedFile = new File([blob], fileName, {
            type: 'image/jpeg',
            lastModified: Date.now()
          });
          console.log(compressedFile.size);
          _callback(compressedFile, self);
        }, 'image/jpeg', AppComponent.IMAGE_QUALITY / 100);
      },
        reader.onerror = error => console.log(error);
    };
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
    uploadData.append('file', this.base64ToFile(localStorage.getItem('file'), localStorage.getItem('filename')));
    localStorage.removeItem('brief');
    localStorage.removeItem('file');
    localStorage.removeItem('filename');
    return uploadData;
  }
}
