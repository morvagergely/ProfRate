import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders, HttpRequest } from '@angular/common/http';
import { of, Observable, throwError } from 'rxjs';
import { tap, catchError, mapTo, map } from 'rxjs/operators';

export interface AccessToken {
  access_token: string;
}

export interface Neptun_password {
  neptunCode: string;
  password: string;
}

enum Operation {
  Register = 'register',
  Login = 'login',
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private http: HttpClient) { }

  private _neptun_password: Neptun_password = { neptunCode: localStorage.getItem('neptunCode'), password: localStorage.getItem('password') };

  get base(): string {
    return 'http://localhost:8080/';
  }

  get isLoggedIn(): boolean {
    return !!this._neptun_password;
  }

  get neptun_password(): string | undefined {
    return this.neptun_password;
  }

  get headers(): HttpHeaders {
    return new HttpHeaders({ 'Authorization': `Basic ${btoa(`${localStorage.getItem('neptunCode')}:${localStorage.getItem('password')}`)}` });
  }

  register(neptunCode: string, password: string): Observable<undefined> {
    localStorage.setItem('neptunCode', neptunCode);
    localStorage.setItem('password', password);
    return this.api<{}>('users/register', { neptunCode: localStorage.getItem('neptunCode'), password: localStorage.getItem('password') }).pipe(
      mapTo(undefined),
    )
  }

  login(neptunCode: string, password: string): Observable<undefined> {
    localStorage.setItem('neptunCode', neptunCode);
    localStorage.setItem('password', password);
    return this.api('users/login', {}).pipe(
      mapTo(undefined),
    );
  }

  logout(): void {
    this._neptun_password = undefined;
    localStorage.removeItem('neptunCode');
    localStorage.removeItem('password');
  }

  api<T>(path: string, body?: any, type?: string): Observable<T> {
    let h = path == 'users/register' ? new HttpHeaders() : this.headers;
    if (!type) {
      console.log(body ? 'POST' : 'GET', path, h);
      return this.http.request<T>(body ? 'POST' : 'GET', `${this.base}${path}`, {
        body,
        observe: 'response',
        headers: h,
      }).pipe(
        map(response => response.body),
        catchError((error: HttpErrorResponse) => throwError(error.status)),
      )
    }
    else {
      console.log(type, path, h);
      return this.http.request<T>(type, `${this.base}${path}`, {
        body,
        observe: 'response',
        headers: h,
      }).pipe(
        map(response => response.body),
        catchError((error: HttpErrorResponse) => throwError(error.status)),
      )
    }
    
  }
}
