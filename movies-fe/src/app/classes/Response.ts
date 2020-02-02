import {JsonResponseBodyInterface} from '../interfaces/JsonResponseBodyInterface';

export class Response implements JsonResponseBodyInterface {
  server: number;
  response: object;
}
