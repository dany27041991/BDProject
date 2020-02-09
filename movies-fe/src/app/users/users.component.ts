import { Component, OnInit } from '@angular/core';
import {AuthService} from '../services/auth.service';
import {MoviePageInterface} from '../interfaces/MoviePageInterface';
import {HttpErrorResponse} from '@angular/common/http';
import {Response} from '../classes/Response';
import {User} from '../classes/User';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  public users = new Array<Object>();
  public flagDeletion = false;
  public selectedUserId = 0;
  public mexError = '';
  constructor(private userService: AuthService) {
    this.getAllUsers();
    this.flagDeletion = false;
    this.selectedUserId = 0;
    this.mexError = '';
  }

  ngOnInit() {
  }

  getAllUsers() {
    this.userService.getAllContacts().subscribe(
      (payload: Response) => {
        console.log(payload);
        this.users = payload.response as Array<Object>;
      },
      (httpResp: HttpErrorResponse) => {
        this.mexError = 'A problem occurred, try later!';
        this.flagDeletion = false;
        setTimeout(() => {
          this.mexError = '';
        }, 2000);
      });
  }

  deleteUser(iduser: number) {
    this.selectedUserId = iduser;
    this.flagDeletion = true;
  }

  comeBack() {
    this.selectedUserId = 0;
    this.flagDeletion = false;
  }

  confirmRemove() {
    if (this.selectedUserId !== 0) {
      this.userService.deleteUser(this.selectedUserId).subscribe(
        (payload: Response) => {
          console.log(payload);
          this.getAllUsers();
          this.mexError = '';
          this.flagDeletion = false;
          this.selectedUserId = 0;
        },
        (httpResp: HttpErrorResponse) => {
          this.mexError = 'A problem occurred during the deletion, try later!';
          this.flagDeletion = false;
          this.selectedUserId = 0;
          setTimeout(() => {
            this.mexError = '';
          }, 2000);
        });
    } else {
      this.mexError = 'A problem occurred, try later!';
      this.flagDeletion = false;
      this.selectedUserId = 0;
      setTimeout(() => {
        this.mexError = '';
      }, 2000);
    }
  }
}
