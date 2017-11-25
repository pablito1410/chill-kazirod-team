import {Injectable} from '@angular/core';
import {AbstractService} from './abstract.service';
import {HttpClient} from '@angular/common/http';
import {Invitation} from '../data/invitation';

@Injectable()
export class InvitationsService extends AbstractService<Invitation> {

    constructor(protected http: HttpClient) {
        super(http);
        this.url = 'api/acceptation/player/';
    }

    getInvitations(userId: number){
        this.url += +userId;
        return super.get();
    }

}
