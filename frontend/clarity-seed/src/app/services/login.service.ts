import { Injectable } from '@angular/core';
import {AuthHttp} from 'angular2-jwt';
import {User} from '../data/user';
import {environment} from '../../environments/environment';
import {HttpHeaders} from '@angular/common/http';
import {Headers, RequestOptions} from '@angular/http';

@Injectable()
export class LoginService {

  constructor(protected authHttp: AuthHttp) { }

  login(user: User){
      let url = environment.envUrl + '/login';
      let body = JSON.stringify(user);

       let headers = new Headers({
          "Content-Type": "application/json",
          "Accept": "application/json"
      });
      const reqOpts = new RequestOptions({headers: headers});
      return this.authHttp.post(url, body, reqOpts)
  }

}
