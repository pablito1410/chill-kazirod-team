import {AbstractService} from './abstract.service';
import {User} from '../data/user';
import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Http} from '@angular/http';

@Injectable()
export class UserService extends AbstractService<User> {

    constructor(protected http: HttpClient) {
        super(http);
        this.url = '/users';
    }

    get(){
        this.url = '/api/users/all';
        return super.get();
    }

    post(item: User){
        this.url = '/api/users/create';
        return super.post(item);
    }
}
