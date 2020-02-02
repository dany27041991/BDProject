import {Component, OnInit} from '@angular/core';
import {ModalDismissReasons, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {NgForm} from '@angular/forms';
import {ContactService} from '../services/contact.service';
import {User} from '../classes/User';

@Component({
  selector: 'app-modal-basic',
  templateUrl: './modal-basic.component.html',
  styleUrls: ['./modal-basic.component.css']
})
export class ModalBasicComponent implements OnInit {
  public closeResult: string;
  public mobnumPattern = '^((\\+91-?)|0)?[0-9]{10}$';
  constructor(private modalService: NgbModal, private contactservice: ContactService) {}

  ngOnInit() {}

  open(content) {
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

  addContact(form: NgForm) {
    const addcontact = confirm('Do you really want add the contact?');
    if (addcontact) {
      let user = new User();
      user = JSON.parse(localStorage.getItem('user'));
      this.contactservice.addContact(form.value.name, form.value.surname, form.value.housenumber,
        form.value.cellnumber, form.value.address, form.value.detail, user['id_user']);
      this.modalService.dismissAll(ModalDismissReasons.ESC);
    }
  }

}
