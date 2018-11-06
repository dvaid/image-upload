import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from "./components/login/login.component";
import { RegisterComponent } from "./components/register/register.component";
import { ProfileComponent } from "./components/profile/profile.component";
import { AuthGuard } from "./urlPermission/url.permission";
import { ImageUploadComponent } from './components/image-upload/image-upload.component';
import { HomeLayoutComponent } from './components/layouts/home-layout/home-layout.component';
import { LoginLayoutComponent } from './components/layouts/login-layout/login-layout.component';


const appRoutes: Routes = [{
  path: 'home',                       // {1}
  component: HomeLayoutComponent,
  children: [{
    path: '',
    pathMatch: 'full',
    redirectTo: 'upload'
  },
  {
    path: 'upload',
    canActivate: [AuthGuard],
    component: ImageUploadComponent   // {3}
  },
  { path: 'profile', component: ProfileComponent, canActivate: [AuthGuard] },
  ]
}, {
  path: '',
  component: LoginLayoutComponent, // {4}
  children: [{
    path: '',
    pathMatch: 'full',
    redirectTo: 'upload'
  },
  {
    path: 'login',
    component: LoginComponent   // {5}
  }, {
    path: 'upload',
    component: ImageUploadComponent   // {3}
  },
  { path: 'register', component: RegisterComponent },
  ]
},

// otherwise redirect to login
{ path: '**', redirectTo: '/login' }
];

export const routing = RouterModule.forRoot(appRoutes);
