import {Component, OnInit} from '@angular/core';
import {User} from '../../data/user';
import {UserService} from '../../services/user.service';
import {GameService} from '../../services/game.service';
import {Game} from '../../data/game';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Team} from '../../data/team';

@Component({
    templateUrl: './users-list.component.html',
    styleUrls: ['./users-list.component.css']
})
export class UsersListComponent implements OnInit {

    showModal: boolean = false;

    users: User[] = [];

    game: Game = new Game();

    anySelected: boolean = false;

    firstTeam: Team = new Team();

    secondTeam: Team = new Team();

    form: FormGroup = this.fb
        .group({
            gameDate: ['', Validators.required]
        });

    constructor(protected usersService: UserService,
                protected gameService: GameService,
                protected fb: FormBuilder) {
    }

    ngOnInit() {
        this.usersService.get()
            .subscribe((users: User[]) => this.users = users);
    }

    addToFirstTeam(selectedUser: User) {
        if (this.firstTeam.users.length < 2) {
            this.firstTeam.users.push(selectedUser);
        } else { // TODO: NIE MA MIEJSC W DRUZYNIE
        }
    }

    addToSecondTeam(selectedUser: User) {
        if (this.secondTeam.users.length < 2) {
            this.secondTeam.users.push(selectedUser);
        } else { // TODO: NIE MA MIEJSC W DRUZYNIE
        }
    }

    openModal() {
        this.showModal = true;
    }

    cancel() {
        this.firstTeam.users = [];
        this.secondTeam.users = [];
    }

    createGame() {
        if (this.form.get('dateTime').valid) {
            this.game.date = this.form.get('dateTime').value;
            this.gameService.post(this.game)
        }
    }

}
