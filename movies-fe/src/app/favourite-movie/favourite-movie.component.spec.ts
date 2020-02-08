import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FavouriteMovieComponent } from './favourite-movie.component';

describe('FavouriteMovieComponent', () => {
  let component: FavouriteMovieComponent;
  let fixture: ComponentFixture<FavouriteMovieComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FavouriteMovieComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FavouriteMovieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
