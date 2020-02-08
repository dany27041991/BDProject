import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-movie-viewer',
  templateUrl: './movie-viewer.component.html',
  styleUrls: ['./movie-viewer.component.css']
})
export class MovieViewerComponent implements OnInit {
  @Input() selectedMovie;
  constructor() { }

  ngOnInit() {
  }

}
