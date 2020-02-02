import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalBasicUpdateComponent } from './modal-basic-update.component';

describe('ModalBasicUpdateComponent', () => {
  let component: ModalBasicUpdateComponent;
  let fixture: ComponentFixture<ModalBasicUpdateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ModalBasicUpdateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModalBasicUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
