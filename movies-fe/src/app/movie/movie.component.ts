import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {MovieService} from '../services/movie.service';
import {MoviePageInterface} from '../interfaces/MoviePageInterface';
import {
  trigger,
  state,
  style,
  animate,
  transition,
  // ...
} from '@angular/animations';
import {ModalDismissReasons, NgbModal, NgbRatingConfig} from '@ng-bootstrap/ng-bootstrap';
import {StarRatingComponent} from 'ng-starrating';
import {User} from '../classes/User';
import {NgForm} from '@angular/forms';
import {Response} from '../classes/Response';
import {HttpErrorResponse} from '@angular/common/http';
import {Router} from '@angular/router';
import {DatePipe} from '@angular/common';

@Component({
  selector: 'app-movie',
  templateUrl: './movie.component.html',
  styleUrls: ['./movie.component.css'],
  animations: [
    trigger('flyInOut', [
      state('in', style({opacity: 1, transform: 'translateX(0)'})),
      transition('void => *', [
        style({
          opacity: 0,
          transform: 'translateX(0%)'
        }),
        animate('2s ease-in')
      ]),
    ])
  ]
})
export class MovieComponent implements OnInit {
  public moviePage: MoviePageInterface;
  private page = 1;
  public closeResult: string;
  public isSuperUser = false;
  public mexUpdate = '';
  public mexWrongUpdate = '';
  public selectedMovie: Object = {};
  private user = new User();
  public movieToAdd: Object = null;
  public mexAdded = '';
  public mexWrongAdded = '';
  public confirmFlag = false;
  constructor(private movieService: MovieService, config: NgbRatingConfig, private modalService: NgbModal, private router: Router,
              private datePipe: DatePipe) {
    config.max = 5;
    this.movieService.getAllMovies(this.page).subscribe(
      (payload: Response) => {
        this.moviePage = <MoviePageInterface>payload.response;
      },
      (httpResp: HttpErrorResponse) => {
        this.router.navigate(['error']);
      });
  }

  ngOnInit() {
    this.user = JSON.parse(localStorage.getItem('user'));
    if (this.user.email === 'superuser@gmail.com') {
      this.isSuperUser = true;
    }
  }

  getNewPageMovies() {
    this.movieService.getAllMovies(this.page).subscribe(
      (payload: Response) => {
        this.moviePage = <MoviePageInterface>payload.response;
      },
      (httpResp: HttpErrorResponse) => {
        this.router.navigate(['error']);
      });
  }

  onRate($event: {oldValue: number, newValue: number, starRating: StarRatingComponent}, idmovie: number) {
    this.movieService.addToRate(this.user.id_user, idmovie, $event.newValue).subscribe(
      (payload: Response) => {
        this.getNewPageMovies();
      },
      (httpResp: HttpErrorResponse) => {
        this.router.navigate(['error']);
      });
  }

  setRateOnStars(ratingsObj) {
    let rating = 0;
    const id_user = this.user.id_user;
    ratingsObj.forEach(function (obj) {
      if (obj.iduser === id_user) {
        rating = obj.rating;
      }
    });
    return rating;
  }

  getAverageRateMovie(idmovie, ratingsObj) {
    if (ratingsObj.length > 0) {
      let total = 0;
      let count = 0;
      ratingsObj.forEach(function (obj) {
        if (idmovie === obj.idmovie) {
          total += obj.rating;
          count++;
        }
      });
      if (Number.isInteger(total / count)) {
        return (total / count);
      }
      return (total / count).toFixed(2);
    } else {
      return 0;
    }
  }

  updateMovie(form: NgForm) {
    this.movieService.updateMovie(form, this.selectedMovie['idmovie']).subscribe(
      async (payload: Response) => {
        this.mexUpdate = 'Updated Successfully!';
        await setTimeout(() => {
          this.modalService.dismissAll();
          this.mexUpdate = '';
          this.getNewPageMovies();
        }, 2000);
      },
      async (httpResp: HttpErrorResponse) => {
        this.mexWrongUpdate = 'A Problem Occurred. Try Later!';
        await setTimeout(() => {
          this.modalService.dismissAll();
          this.mexWrongUpdate = '';
        }, 2000);
      });
  }

  async searchMovie(title) {
    if (title) {
      this.movieService.checkMovieByTitle(title).subscribe(
        async (payload: Response) => {
          if (!payload.response) {
            this.movieService.searchMovieByTitle(title).subscribe(
              (jsonMovie) => {
                if (jsonMovie['Response'] === 'False') {
                  this.mexWrongAdded = 'Sorry, movie not found!';
                } else {
                  this.movieToAdd = {
                    'title': jsonMovie['Title'],
                    'year': jsonMovie['Year'],
                    'released': this.datePipe.transform(jsonMovie['Released'], 'yyyy-MM-dd'),
                    'runtime': jsonMovie['Runtime'],
                    'genre': jsonMovie['Genre'],
                    'director': jsonMovie['Director'],
                    'writer': jsonMovie['Writer'],
                    'actors': jsonMovie['Actors'],
                    'plot': jsonMovie['Plot'],
                    'language': jsonMovie['Language'],
                    'country': jsonMovie['Country'],
                    'adwards': jsonMovie['Awards'],
                    'poster': jsonMovie['Poster'],
                    'dvd':  this.datePipe.transform(jsonMovie['DVD'], 'yyyy-MM-dd'),
                    'production': jsonMovie['Production']
                  };
                }
              },
              async (httpResp: HttpErrorResponse) => {
                this.mexWrongAdded = 'A problem occurred, try later!';
                await setTimeout(() => {}, 2000);
              });
          } else {
            this.mexWrongAdded = 'Sorry, but this movie already exists!';
            await setTimeout(() => {}, 2000);
          }
        },
        async (httpResp: HttpErrorResponse) => {
          this.mexWrongAdded = 'A problem occurred, try later!';
          await setTimeout(() => {}, 2000);
        });
    }
    this.mexWrongAdded = '';
    this.movieToAdd = null;
  }

  addMovie() {
    this.movieService.addMovie(this.movieToAdd).subscribe(
      async (payload: Response) => {
        this.mexAdded = 'Added Successfully!';
        await setTimeout(() => {
          this.modalService.dismissAll();
          this.mexAdded = '';
          this.getNewPageMovies();
        }, 2000);
      },
      async (httpResp: HttpErrorResponse) => {
        this.mexWrongAdded = 'A problem occurred, try later!';
        await setTimeout(() => {}, 2000);
      });
    this.mexWrongAdded = '';
    this.mexAdded = '';
    this.movieToAdd = null;
  }

  comeBack() {
    this.movieToAdd = null;
    this.mexWrongAdded = '';
    this.mexAdded = '';
  }

  addFavourite(movie) {
    this.movieService.addToFavourite(this.user.id_user, movie['idmovie']).subscribe(
      (payload: Response) => {
        this.getNewPageMovies();
      },
      (httpResp: HttpErrorResponse) => {
        this.router.navigate(['error']);
      });
  }

  setColorToHeart(addFavouritesObj) {
    const id_user = this.user.id_user;
    let style = {
      'color': 'white'
    };
    const existFavourite = addFavouritesObj.some(obj => obj.iduser === id_user);
    if (existFavourite) {
      style.color = 'red';
      return style;
    } else {
      return style;
    }
  }

  openMovieDetail(content, movie) {
    this.selectedMovie = movie;
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  openMovieUpdate(content, movie) {
    this.selectedMovie = movie;
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-update-movie', size: 'lg'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  openModalToAddMovie(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-add-movie', size: 'lg'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  selectionDeleteMovie() {
    this.confirmFlag = true;

  }

  notConfirm() {
    this.confirmFlag = false;
  }

  confirmedDelete(idmovie: number) {
    this.movieService.deleteMovie(idmovie).subscribe(
      async (payload: Response) => {
        this.mexUpdate = 'Deleted Successfully!';
        await setTimeout(() => {
          this.modalService.dismissAll();
          this.mexUpdate = '';
          this.getNewPageMovies();
        }, 2000);
      },
      async (httpResp: HttpErrorResponse) => {
        this.mexWrongUpdate = 'A Problem Occurred. Try Later!';
        await setTimeout(() => {
          this.modalService.dismissAll();
          this.mexWrongUpdate = '';
        }, 2000);
      });
    this.confirmFlag = false;
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return  `with: ${reason}`;
    }
  }
}
