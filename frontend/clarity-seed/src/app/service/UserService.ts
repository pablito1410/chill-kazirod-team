import {AbstractService} from './AbstractService';
import {User} from '../data/User';
import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';

@Injectable()
export class UserService extends AbstractService<User> {

    constructor(protected http: HttpClient) {
        super(http);
        this.url = '/users';
    }
}
