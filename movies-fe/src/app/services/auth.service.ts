import {EventEmitter, Injectable, Output} from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {JsonResponseBodyInterface} from '../interfaces/JsonResponseBodyInterface';
import {Response} from '../classes/Response';

@Injectable()
export class AuthService {
  private isUserLogged = false;
  @Output() register = new EventEmitter<Response>();
  @Output() logged = new EventEmitter<Response>();
  @Output() isuserlogged = new EventEmitter<boolean>();
  private APIAUTHURL = 'http://localhost:8090/';
  constructor(private http: HttpClient) { }
  login(email: string, password: string) {
    const resp = new Response();
    this.http.post(this.APIAUTHURL + 'login',
      {
        email: email,
        password: password
      }
  ).subscribe(
      (payload: Response) => {
        localStorage.setItem('user', JSON.stringify(payload.response));
        resp.server = 200;
        resp.response = payload;
        this.isUserLogged = true;
        this.isuserlogged.emit(true);
        this.logged.emit(resp);
      },
      (httpResp: HttpErrorResponse) => {
        resp.server = httpResp.error['server'];
        resp.response = httpResp.error.response;
        this.isUserLogged = false;
        this.isuserlogged.emit(false);
        this.logged.emit(resp);
      }
    );
  }
  signUp(name: string, lastname: string, email: string, password: string) {
    const resp = new Response();
    this.http.post(this.APIAUTHURL + 'signup',
      {
        name: name,
        lastname: lastname,
        email: email,
        password: password
      }
    ).subscribe(
      (payload: JsonResponseBodyInterface) => {
        resp.server = payload.server;
        resp.response = payload.response
        this.register.emit(resp);
      },
      (httpResp: HttpErrorResponse) => {
        resp.response = httpResp.error.response;
        this.register.emit(resp);
      }
    );
    return true;
  }
  logout() {
    localStorage.clear();
    this.isUserLogged = false;
    return true;
  }
}
