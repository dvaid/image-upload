import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  
  static API_URL="http://localhost:9001";
  static PAYMENT_URL = "https://test.avantgardepayments.com/agcore/payment";
  static IMAGE_QUALITY = 99.99;
  
}
