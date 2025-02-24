import { HttpClient, HttpResponse } from '@angular/common/http';
import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { catchError, map, of } from 'rxjs';

export const securityGuard: CanActivateFn = (route, state) => {

  const http = inject(HttpClient);
  const router = inject(Router);
  const apiUrl = 'http://localhost:8080/api/booking/?page=0&size=1';


  return http.get(apiUrl, { observe: 'response', withCredentials: true }).pipe(
    map((response: HttpResponse<any>) => {
     
      return response.status === 200;
      
    }),
    catchError((error) => {

      router.navigate(['/login']);
      return of(false);
    })
  );

};
