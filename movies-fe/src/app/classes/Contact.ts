import {ContactInterface} from '../interfaces/ContactInterface';

export class Contact implements ContactInterface {
  idcontact: number;
  name: string;
  surname: string;
  housenumber: string;
  cellnumber: string;
  address: string;
  detail: string;
  iduser: number;
  constructor() {
    this.idcontact = null;
    this.name = null;
    this.surname = null;
    this.housenumber = null;
    this.cellnumber = null;
    this.address = null;
    this.detail = null;
    this.iduser = null;
  }
}
