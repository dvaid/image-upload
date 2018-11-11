import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from "./components/login/login.component";
import { RegisterComponent } from "./components/register/register.component";
import { AuthGuard } from "./urlPermission/url.permission";
import { ImageUploadComponent } from './components/image-upload/image-upload.component';
import { HomeComponent } from './components/home/home.component';
import { AboutUsComponent } from './components/about-us/about-us.component';
import { JuryComponent } from './components/jury/jury.component';
import { HowToEnterComponent } from './components/how-to-enter/how-to-enter.component';
import { PartenersComponent } from './components/parteners/parteners.component';
import { PrizeFeeComponent } from './components/prize-fee/prize-fee.component';
import { GalleryComponent } from './components/gallery/gallery.component';
import { MySubmissionsComponent } from './components/my-submissions/my-submissions.component';

const appRoutes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: '/home' },
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'about-us', component: AboutUsComponent },
  { path: 'jury', component: JuryComponent },
  { path: 'partners', component: PartenersComponent },
  { path: 'how-to-enter', component: HowToEnterComponent },
  { path: 'prize-fee', component: PrizeFeeComponent },
  { path: 'gallery', component: GalleryComponent },
  { path: 'upload', component: ImageUploadComponent },
  { path: 'my-submissions', component: MySubmissionsComponent, canActivate: [AuthGuard] },
  { path: 'register', component: RegisterComponent },
  { path: '**', redirectTo: '/login' }
];

export const routing = RouterModule.forRoot(appRoutes);

// {

//   path: '',                       // {1}
//   component: HomeLayoutComponent,
//   children: [{
//     path: '',
//     pathMatch: 'full',
//     redirectTo: 'upload'
//   },
//   {
//     path: 'upload',
//     canActivate: [AuthGuard],
//     component: ImageUploadComponent   // {3}
//   },
//   { path: 'profile', component: ProfileComponent, canActivate: [AuthGuard] },
//   ]
// }, {
//   path: '',
//   component: LoginLayoutComponent, // {4}
//   children: [{
//     path: '',
//     pathMatch: 'full',
//     redirectTo: 'upload'
//   },
//   {
//     path: 'login',
//     component: LoginComponent   // {5}
//   }, {
//     path: 'upload',
//     component: ImageUploadComponent   // {3}
//   },
//   { path: 'register', component: RegisterComponent },
//   ]
// },