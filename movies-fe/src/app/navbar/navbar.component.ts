import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {AuthService} from '../services/auth.service';
import {User} from '../classes/User';
import {NgbModalConfig, NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import {NgForm} from '@angular/forms';
import {Response} from '../classes/Response';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
  providers: [NgbModalConfig, NgbModal]
})
export class NavbarComponent implements OnInit {
  public control: boolean;
  public mex: string;
  public mexOk: string;
  public mexFail: string;
  public isUserLoggedIn = false;
  public isSuperUser = false;
  public name: string;
  public closeResult: string;
  constructor(private auth: AuthService, private router: Router, config: NgbModalConfig, private modalService: NgbModal) {
    config.backdrop = 'static';
    config.keyboard = false;
    this.name = '';
    auth.isuserlogged.subscribe((resp: boolean) => {
        let user = new User();
        user = JSON.parse(localStorage.getItem('user'));
        if (user === null) {
          this.name = '';
          this.isUserLoggedIn = false;
          this.isSuperUser = false;
        } else {
          this.name = user.name;
          this.isUserLoggedIn = resp;
          if (user.email === 'superuser@gmail.com') {
            this.isSuperUser = true;
          }
        }
      }
    );

    this.control = false;
    this.auth.register.subscribe((resp: Response) =>
      (
        this.mexFail = '',
          this.mexOk = '',
          this.setMessageAndRedirect(resp)
      )
    );

    this.auth.logged.subscribe((resp: Response) => (
        this.mex = '',
          this.controlAndSetMessage(resp)
      )
    );
  }

  ngOnInit() {
    if (localStorage.getItem('user')) {
      this.isUserLoggedIn = true;
      setTimeout(() => {
        let user = new User;
        user = JSON.parse(localStorage.getItem('user'));
        this.name = user.name;
        if (user.email === 'superuser@gmail.com') {
          this.isSuperUser = true;
        }
      }, 1);
    } else {
      this.isUserLoggedIn = false;
      this.isSuperUser = false;
    }
  }

  openSignIn(content) {
    this.mex = null;
    this.modalService.open(content);
  }

  openSignUp(content) {
    this.mexOk = null;
    this.mexFail = null;
    this.modalService.open(content);
  }

  login(form: NgForm) {
    this.auth.login(form.value.email, form.value.password);
  }

  signUp(form: NgForm) {
    this.auth.signUp(form.value.name, form.value.lastname, form.value.email, form.value.password);
  }

  logout(e) {
    if (this.auth.logout()) {
      this.isUserLoggedIn = false;
      this.isSuperUser = false;
      e.preventDefault();
      setTimeout(() => {
        this.router.navigate(['']);
      }, 300);
    }
  }

  controlAndSetMessage(resp: Response) {
    if (resp.server === 200) {
      this.modalService.dismissAll();
      setTimeout(() => {
        let user = new User;
        user = JSON.parse(localStorage.getItem('user'));
        this.router.navigate(['movies']);
      }, 1);
    } else {
      if (resp.server === 404) {
        this.mex = JSON.stringify(resp.response);
        this.dismissModalTimeout(2500);
      } else {
        if (resp.server === 403) {
          this.mex = JSON.stringify(resp.response);
          this.dismissModalTimeout(2500);
        } else {
          this.mex = 'Problems connecting to the database, try again later!';
          this.dismissModalTimeout(2500);
        }
      }
    }
  }

  setMessageAndRedirect(resp: Response) {
    if (resp.server === 200) {
      this.control = true;
      this.mexOk = resp.response + ' Modal dismiss in 3 seconds!';
      this.dismissModalTimeout(3000);
    } else {
      this.mexFail = 'Problems connecting to the database, try again later!';
      this.dismissModalTimeout(2500);
    }
  }

  dismissModalTimeout(time) {
    setTimeout(() => {
      this.modalService.dismissAll();
    }, time);
  }

  openUsersManagement(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title', size: 'lg'}).result.then((result) => {
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
