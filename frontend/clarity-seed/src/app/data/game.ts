import {AbstractItem} from './abstract-item';
import {Team} from './team';

export class Game extends AbstractItem {

    firstTeam: Team;

    secondTeam: Team;

    firstScore: number;

    secondScore: number;

    date: Date;

    status: string;

}
