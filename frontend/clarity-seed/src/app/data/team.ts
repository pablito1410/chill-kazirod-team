
import {AbstractItem} from './abstract-item';
import {User} from './user';

export class Team extends AbstractItem {

    users: User[] = []

    name: string;
}