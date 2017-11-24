import {Component, OnInit} from '@angular/core';
import {User} from '../../data/User';
import {UserService} from '../../service/UserService';

@Component({
    templateUrl: './users-list.component.html',
    styleUrls: ['./users-list.component.css']
})
export class UsersListComponent implements OnInit {

    users: User[] = [];

    anySelected: boolean = false;

    constructor(protected usersService: UserService) {
    }

    ngOnInit() {
        this.usersService.get()
            .subscribe((users: User[]) => this.users = users);
    }

}
