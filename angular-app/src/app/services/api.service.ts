import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Login } from '../models/login';
import { SeatsPageable } from '../models/seats-pageable';
import { Seat } from '../models/seat-response';
import { catchError, map, Observable, of, tap } from 'rxjs';
import { BookingResponse } from '../models/element';

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

  allSeats(): Observable<Seat[]> {
    return this.http.get<Seat[]>(
      `${this.apiUrl}/api/seat/`,
      { withCredentials: true }
    );
  }

  findbySeat(id : String){
    return this.http.get<BookingResponse>(`${this.apiUrl}/api/booking/findbySeat/{id}?id=${id}`
    , { withCredentials: true });
  }

  bookingSeat(id : String, acao : Boolean){
    
    const requestBody = {
      seatId: (acao) ? id : "",
      acao: (acao) ? "ALOCACAO" : "REMOCAO" 
    };


    const method = (acao) ? this.http.post.bind(this.http): this.http.put.bind(this.http);
    const param = (acao) ? "" : "?id=" + id;

    method(this.apiUrl + `/api/booking/${param}`, requestBody,
    { withCredentials: true }).subscribe({
      next: (response) => {
        console.log(response);
      },
      error: (error) => {
        if (error.status === 400) {
          alert(error.error.mensagem);
        }    
      }
    });

  }
  
  logout(): void {
    this.http
      .post<HttpResponse<any>>(this.apiUrl + "/api/auth/logout", {}, { 
        withCredentials: true, 
        observe: 'response' 
      })
      .pipe(
        tap((response: HttpResponse<any>) => {
          if (response.status === 204) {
            this.router.navigate(['/login']);
          }
        }),
        catchError((error) => {
          this.router.navigate(['/login']);
          return of(false);
        })
      )
      .subscribe();
  }
  
}
