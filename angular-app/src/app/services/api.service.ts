import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Login } from '../models/login';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private apiUrl = 'http://localhost:8080';
  
  constructor(private http: HttpClient, private router: Router) {}

  login(loginData: Login): void {

    const requestBody = {
      username: loginData.email,
      password: loginData.senha
    };

    this.http.post<{ mensagem: string; status: string }>(this.apiUrl + "/api/auth/login", requestBody, { withCredentials: true }).subscribe({
      next: (response) => {
        if (response.status === 'OK') {

          this.router.navigate(['/dashboard-seat-manager']);
        }
      },
      error: (error) => { 
        if (error.status === 400) {
          alert(error.error.mensagem);
        }

        
      }
    });


  }
}
