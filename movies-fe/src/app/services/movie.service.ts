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

  addToRate(id_user, idmovie, rating) {
    return this.http.post(this.APIAUTHURL + 'add-rate',
      {
        iduser: id_user,
        idmovie: idmovie,
        rating: rating
      }
    );
  }

  updateMovie(form, idmovie) {
    return this.http.post(this.APIAUTHURL + 'update',
      {
        'idmovie': idmovie,
        'country': form.value.country,
        'production': form.value.production,
        'plot': form.value.plot,
        'adwards': form.value.adwards,
        'dvd': form.value.dvd,
        'released': form.value.released,
        'year': form.value.year,
        'runtime': form.value.runtime
      }
    );
  }

  checkMovieByTitle(title) {
    return this.http.get(this.APIAUTHURL + 'search/' + title);
  }

  searchMovieByTitle(title) {
    return this.http.get('http://www.omdbapi.com/?apikey=660ad911&t=' + title);
  }

  addMovie(movie) {
    return this.http.post(this.APIAUTHURL + 'add', movie);
  }

  deleteMovie(idmovie) {
    return this.http.get(this.APIAUTHURL + 'delete-movie/' + idmovie);
  }

  getFavouriteMovies(iduser: number) {
    return this.http.get(this.APIAUTHURL + 'favourite/' + iduser);
  }

  removeMovieFromFavourite(idmovie, iduser) {
    return this.http.post(this.APIAUTHURL + 'remove-from-favourite', {
      'iduser': iduser,
      'idmovie': idmovie
    });
  }
}
