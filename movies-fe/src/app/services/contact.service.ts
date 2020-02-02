import {EventEmitter, Injectable, Output} from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {User} from '../classes/User';
import {Response} from '../classes/Response';
import {Contact} from '../classes/Contact';

@Injectable()
export class ContactService {
  private APIAUTHURL = 'http://localhost:8090/contacts/';
  @Output() modalflag = new EventEmitter<any>();
  @Output() modalflagupdate = new EventEmitter<any>();
  @Output() getcontact = new EventEmitter<boolean>();
  @Output() selectedcontact = new EventEmitter<Contact>();
  constructor(private http: HttpClient) {}
  getAllContacts() {
    let user = new User;
    user = JSON.parse(localStorage.getItem('user'));
    this.http.get(this.APIAUTHURL + user['id_user']).subscribe(
      (payload: Response) => {
        localStorage.setItem('contacts', JSON.stringify(payload.response));
        this.getcontact.emit(true);
      },
      (httpResp: HttpErrorResponse) => {
        this.getcontact.emit(false);
      }
    );
  }

  deleteContact(contact: Contact) {
    let user = new User();
    user = JSON.parse(localStorage.getItem('user'));
    this.http.get(this.APIAUTHURL + user['id_user'] + '/delete/' + contact.idcontact).subscribe(
      (payload: Response) => {
        localStorage.setItem('contacts', JSON.stringify(payload.response));
        alert('Contact deleted properly!');
      },
      (httpResp: HttpErrorResponse) => {
        alert('Problems with deletion. Try later!');
      }
    );
  }

  addContact(name: string, surname: string, housenumber: string, cellnumber: string, address: string, detail: string, iduser: number) {
    this.http.put(this.APIAUTHURL + 'addcontact',
      {
        name: name,
        surname: surname,
        housenumber: housenumber,
        cellnumber: cellnumber,
        address: address,
        detail: detail,
        iduser: iduser
      }
    ).subscribe(
      (payload: Response) => {
        localStorage.setItem('contacts', JSON.stringify(payload.response));
        alert('Contact added correctly!');
        this.modalflag.emit(payload.response);
      },
      (httpResp: HttpErrorResponse) => {
        alert('Contact not added correctly. Problems connecting to the DB. Try again later!');
        this.modalflag.emit(false);
      }
    );
  }

  updateContact(idcontact: number, name: string, surname: string, housenumber: string, cellnumber: string,
                address: string, detail: string, iduser: number) {
    this.http.put(this.APIAUTHURL + 'update',
      {
        idcontact: idcontact,
        name: name,
        surname: surname,
        housenumber: housenumber,
        cellnumber: cellnumber,
        address: address,
        detail: detail,
        iduser: iduser
      }
    ).subscribe(
      (payload: Response) => {
        localStorage.setItem('contacts', JSON.stringify(payload.response));
        alert('Contact update correctly!');
        this.modalflagupdate.emit(payload.response);
      },
      (httpResp: HttpErrorResponse) => {
        alert('Contact not update correctly. Problems connecting to the DB. Try again later!');
        this.modalflagupdate.emit(false);
      }
    );
  }
}
