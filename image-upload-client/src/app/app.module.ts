import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { FormsModule } from '@angular/forms';
import { AuthService } from "./services/auth.service";
import { HttpModule } from "@angular/http";
import { AccountService } from "./services/account.service";
import { ProfileComponent } from './components/profile/profile.component';
import { routing } from "./app.routing";
import { FacebookModule } from "ngx-facebook";
import { UrlPermission } from "./urlPermission/url.permission";
import { MatToolbarModule, MatButtonModule, MatSidenavModule, MatIconModule, MatListModule, MatCardModule, MatProgressBarModule } from '@angular/material';
import { ImageUploadComponent } from './components/image-upload/image-upload.component';
import { HttpClientModule } from '@angular/common/http';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    ProfileComponent,
    ImageUploadComponent
  ],
  imports: [
    BrowserModule, HttpModule, FormsModule, routing, FacebookModule.forRoot(), MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatCardModule,
    MatProgressBarModule,
    HttpClientModule
  ],
  providers: [AuthService, AccountService, UrlPermission],
  bootstrap: [AppComponent]
})
export class AppModule { }
