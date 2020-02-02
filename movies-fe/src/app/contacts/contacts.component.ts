import { Component, OnInit } from '@angular/core';
import {ContactService} from '../services/contact.service';
import {Contact} from '../classes/Contact';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-contacts',
  templateUrl: './contacts.component.html',
  styleUrls: ['./contacts.component.css']
})
export class ContactsComponent implements OnInit {
  public contacts: Contact[];
  public numberofcontacts: number;
  public flag = true;
  constructor(private contact: ContactService, private modalService: NgbModal) {
    contact.getAllContacts();
    contact.getcontact.subscribe((resp: boolean) => {
      if (resp) {
        this.contacts = JSON.parse(localStorage.getItem('contacts'));
        this.numberofcontacts = this.contacts.length;
        this.flag = true;
      } else {
        this.flag = false;
      }
    });

    contact.modalflag.subscribe((resp: any) => {
      if (resp !== false) {
        this.contacts = resp;
        this.numberofcontacts ++;
      }
    });

    contact.modalflagupdate.subscribe((resp: any) => {
      if (resp !== false) {
        this.contacts = resp;
      }
    });
  }

  ngOnInit() {
  }

  deleteContact(contactResp: Contact) {
    const deletecontact = confirm('Do you really want remove the contact number ' + contactResp.idcontact + '?');
    if (deletecontact) {
      this.contact.deleteContact(contactResp);
      this.contacts = this.contacts.filter(obj => obj !== contactResp);
      this.numberofcontacts --;
    }
  }
}
