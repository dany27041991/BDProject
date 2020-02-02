import {Component, Input, OnInit} from '@angular/core';
import {ModalDismissReasons, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {ContactService} from '../services/contact.service';
import {User} from '../classes/User';
import {NgForm} from '@angular/forms';
import {Contact} from '../classes/Contact';

@Component({
  selector: 'app-modal-basic-update',
  templateUrl: './modal-basic-update.component.html',
  styleUrls: ['./modal-basic-update.component.css']
})
export class ModalBasicUpdateComponent implements OnInit {
  @Input() contactselected: Contact;
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

  updateContact(form: NgForm) {
    const updatecontact = confirm('Do you really want update the contact number ' + this.contactselected.idcontact + '?');
    if (updatecontact) {
      this.contactservice.updateContact(this.contactselected.idcontact, form.value.name, form.value.surname, form.value.housenumber,
        form.value.cellnumber, form.value.address, form.value.detail, this.contactselected.iduser);
      this.modalService.dismissAll(ModalDismissReasons.ESC);
    }
  }

}
