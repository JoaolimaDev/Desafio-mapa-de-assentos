import { Routes } from '@angular/router';
import { SeatSelectorComponent } from './seat-selector/seat-selector.component';
import { LoginComponent } from './login/login.component';
import { securityGuard } from './guards/security.guard';

export const routes: Routes = [
    { path: '', redirectTo: 'login', pathMatch: 'full' },
    { path: 'login', component:  LoginComponent}, 
    { path: 'dashboard-seat-manager', component: SeatSelectorComponent, canActivate: [securityGuard] },
    { path: '**', redirectTo: 'login' }  
];
