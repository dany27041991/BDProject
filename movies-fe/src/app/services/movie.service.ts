import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {User} from '../classes/User';
import {Response} from '../classes/Response';

@Injectable()
export class MovieService {
  private APIAUTHURL = 'http://localhost:8090/movies/';
  constructor(private http: HttpClient) {}

  getAllMovies(page: number) {
    return this.http.get(this.APIAUTHURL + page);
  }

  addToFavourite(id_user, idmovie) {
    return this.http.post(this.APIAUTHURL + 'add-favourite',
      {
        iduser: id_user,
        idmovie: idmovie
      }
    );
  }
}
