import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { AuthService } from "./services/auth.service";
import { HttpModule } from "@angular/http";
import { AccountService } from "./services/account.service";
import { ProfileComponent } from './components/profile/profile.component';
import { routing } from "./app.routing";
import { FacebookModule } from "ngx-facebook";
import { AuthGuard } from "./urlPermission/url.permission";
import { MatToolbarModule, MatButtonModule, MatSidenavModule, MatIconModule, MatListModule, MatCardModule, MatProgressBarModule } from '@angular/material';

import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthInterceptor } from './services/auth-interceptor.service';
import { TopNavComponent } from './components/top-nav/top-nav.component';
import { HomeLayoutComponent } from './components/layouts/home-layout/home-layout.component';
import { LoginLayoutComponent } from './components/layouts/login-layout/login-layout.component';
import { HomeComponent } from './components/home/home.component';
import { AboutUsComponent } from './components/about-us/about-us.component';
import { SideNavComponent } from './components/side-nav/side-nav.component';
import { JuryComponent } from './components/jury/jury.component';
import { PartenersComponent } from './components/parteners/parteners.component';
import { HowToEnterComponent } from './components/how-to-enter/how-to-enter.component';
import { PrizeFeeComponent } from './components/prize-fee/prize-fee.component';
import { GalleryComponent } from './components/gallery/gallery.component';
import { FooterComponent } from './components/footer/footer.component';
import { GalleryItemComponent } from './components/gallery/gallery-item/gallery-item.component';
import { MySubmissionsComponent } from './components/my-submissions/my-submissions.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ImageUploadComponent } from './components/image-upload/image-upload.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    ProfileComponent,
    TopNavComponent,
    HomeLayoutComponent,
    LoginLayoutComponent,
    HomeComponent,
    AboutUsComponent,
    SideNavComponent,
    JuryComponent,
    PartenersComponent,
    HowToEnterComponent,
    PrizeFeeComponent,
    GalleryComponent,
    FooterComponent,
    GalleryItemComponent,
    MySubmissionsComponent,
    ImageUploadComponent
  ],
  imports: [
    BrowserModule, HttpModule, FormsModule, routing, FacebookModule.forRoot(), MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatCardModule,
    FormsModule,
    ReactiveFormsModule,
    MatProgressBarModule,
    HttpClientModule
  ],
  providers: [AuthService, AccountService, AuthGuard,
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
