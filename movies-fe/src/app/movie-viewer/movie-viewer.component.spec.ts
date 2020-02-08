import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MovieViewerComponent } from './movie-viewer.component';

describe('MovieViewerComponent', () => {
  let component: MovieViewerComponent;
  let fixture: ComponentFixture<MovieViewerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MovieViewerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MovieViewerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
