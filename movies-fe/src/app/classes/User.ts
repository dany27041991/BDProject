import {UserInterface} from '../interfaces/UserInterface';

export class User implements UserInterface {
  id: number;
  name: string;
  lastname: string;
  email: string;
  constructor() {
    this.id = 0;
    this.name = '';
    this.lastname = '';
    this.email = '';
  }
}
