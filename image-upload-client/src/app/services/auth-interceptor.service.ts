import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthInterceptor implements HttpInterceptor {

  constructor() {

  }
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const basicAuthToken = localStorage.Authorization;
    if (basicAuthToken) {
      req = req.clone({
        setHeaders: {
          'Accept': 'application/json',
          'Authorization': basicAuthToken
        }
      });
    }
    return next.handle(req);
  }
};
