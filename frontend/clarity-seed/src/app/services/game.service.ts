import {Injectable} from '@angular/core';
import {AbstractService} from './abstract.service';
import {Game} from '../data/game';
import {HttpClient} from '@angular/common/http';
import {Http} from '@angular/http';

@Injectable()
export class GameService extends AbstractService<Game> {

    constructor(protected http: HttpClient) {
        super(http);
        this.url = '/api/matches';
    }

}
