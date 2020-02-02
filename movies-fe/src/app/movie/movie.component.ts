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
  closeResult: string;
  public selectedMovie: Object = {};
  constructor(private movie: MovieService, config: NgbRatingConfig, private modalService: NgbModal) {
    config.max = 5;
    movie.getAllMovies(this.page);
    setTimeout(() => {
      this.checkMovies();
    }, 300);
  }

  ngOnInit() {
  }

  checkMovies() {
    if (JSON.parse(localStorage.getItem('moviesPage'))) {
      this.errorMessage = false;
      this.moviePage = JSON.parse(localStorage.getItem('moviesPage'));
    } else {
      this.errorMessage = true;
    }
  }

  getNewPageMovies() {
    console.log(this.page);
    this.movie.getAllMovies(this.page);
    setTimeout(() => {
      this.checkMovies();
    }, 300);
  }

  onRate($event: {oldValue: number, newValue: number, starRating: StarRatingComponent}, idmovie: number) {
    console.log(idmovie);
    console.log(`Old Value:${$event.oldValue}, New Value: ${$event.newValue}, Checked Color: ${$event.starRating.checkedcolor}, 
      Unchecked Color: ${$event.starRating.uncheckedcolor}`);
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
