import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { ApiService } from '../services/api.service';
import { Login } from '../models/login';

@Component({
  selector: 'app-login',
  imports: [ ReactiveFormsModule,
    MatInputModule,
    MatButtonModule,
    MatFormFieldModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  loginForm: FormGroup;


  constructor(private apiService: ApiService) {
    this.loginForm = new FormGroup({
      email: new FormControl(''),
      senha: new FormControl('')
    });
  }


  onSubmit() {
    
    
    const loginData: Login = this.loginForm.value;
    this.apiService.login(loginData);
  }

}
