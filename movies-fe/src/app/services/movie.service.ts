import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {User} from '../classes/User';
import {Response} from '../classes/Response';

@Injectable()
export class MovieService {
  private APIAUTHURL = 'http://localhost:8090/movies/';
  constructor(private http: HttpClient) {}
  getAllMovies(page: number) {
    this.http.get(this.APIAUTHURL + page).subscribe(
      (payload: Response) => {
        localStorage.setItem('moviesPage', JSON.stringify(payload.response));
      },
      (httpResp: HttpErrorResponse) => {
        console.log(httpResp);
      }
    );
  }
}
