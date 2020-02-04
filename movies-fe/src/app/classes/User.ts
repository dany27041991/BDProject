import {UserInterface} from '../interfaces/UserInterface';

export class User implements UserInterface {
  id_user: number;
  name: string;
  lastname: string;
  email: string;
  constructor() {
    this.id_user = 0;
    this.name = '';
    this.lastname = '';
    this.email = '';
  }
}
