import { Component, OnInit } from '@angular/core';
import {User} from '../classes/User';
import {MovieService} from '../services/movie.service';
import {Router} from '@angular/router';
import {Response} from '../classes/Response';
import {MoviePageInterface} from '../interfaces/MoviePageInterface';
import {HttpErrorResponse} from '@angular/common/http';
import {ModalDismissReasons, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {animate, state, style, transition, trigger} from '@angular/animations';

@Component({
  selector: 'app-favourite-movie',
  templateUrl: './favourite-movie.component.html',
  styleUrls: ['./favourite-movie.component.css'],
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
export class FavouriteMovieComponent implements OnInit {
  private user = new User();
  public moviesFavourite = [];
  public selectedMovie: Object = {};
  public closeResult: string;
  constructor(private movieService: MovieService, private router: Router, private modalService: NgbModal) {
    this.getFavouritesMovie();
  }

  ngOnInit() {
    this.user = JSON.parse(localStorage.getItem('user'));
  }

  async getFavouritesMovie() {
    await setTimeout(() => {
      this.movieService.getFavouriteMovies(this.user.id_user).subscribe(
        (payload: Response) => {
          this.moviesFavourite = payload.response as Array<Object>;
        },
        (httpResp: HttpErrorResponse) => {
          this.router.navigate(['error']);
        });
    }, 500);
  }

  removeFromFavourites(idmovie) {
    this.movieService.removeMovieFromFavourite(idmovie, this.user.id_user).subscribe(
      (payload: Response) => {
        console.log(payload);
        this.getFavouritesMovie();
      },
      (httpResp: HttpErrorResponse) => {
        this.router.navigate(['error']);
      });
  }

  openMovieDetail(content, movie) {
    this.selectedMovie = movie;
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
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
