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
          transform: 'translateX(-100%)'
        }),
        animate('2s ease-in')
      ]),
    ])
  ]
})
export class MovieComponent implements OnInit {
  public moviePage: MoviePageInterface;
  private page = 1;
  public errorMessage = false;
  public closeResult: string;
  public isSuperUser = false;
  public mexUpdate = '';
  public selectedMovie: Object = {};
  private user = new User();
  constructor(private movieService: MovieService, config: NgbRatingConfig, private modalService: NgbModal) {
    config.max = 5;
    this.movieService.getAllMovies(this.page).subscribe(
      (payload: Response) => {
        this.moviePage = <MoviePageInterface>payload.response;
      },
      (httpResp: HttpErrorResponse) => {
        console.log(httpResp);
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
        console.log(httpResp);
      });
  }

  onRate($event: {oldValue: number, newValue: number, starRating: StarRatingComponent}, idmovie: number) {
    console.log(idmovie);
    console.log(`Old Value:${$event.oldValue}, New Value: ${$event.newValue}, Checked Color: ${$event.starRating.checkedcolor}, 
      Unchecked Color: ${$event.starRating.uncheckedcolor}`);
  }

  getAverageRateMovie(idmovie, ratingsObj) {
    if (ratingsObj.length > 0) {
      let total = 0;
      ratingsObj.forEach(function (obj) {
        if (idmovie === obj.idmovie_fk) {
          total += obj.rating;
        }
      });
      return total / 5;
    } else {
      return 0;
    }
  }

  updateMovie(form: NgForm) {
    console.log('Update');
  }

  addFavourite(movie) {
    this.movieService.addToFavourite(this.user.id_user, movie['idmovie']).subscribe(
      (payload: Response) => {
        console.log(payload);
        this.getNewPageMovies();
      },
      (httpResp: HttpErrorResponse) => {
        console.log(httpResp);
      });
  }

  setColorToHeart(addFavouritesObj) {
    const id_user = this.user.id_user;
    let style = {
      'color': 'white'
    };
    const existFavourite = addFavouritesObj.some(obj => obj.userId === id_user);
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
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-upadte-movie', size: 'lg'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
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
