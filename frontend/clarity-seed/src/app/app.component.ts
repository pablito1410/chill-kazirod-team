import { Component } from '@angular/core';
import { Router } from '@angular/router';
import {LoggedUserService} from './services/logged-user.service';

@Component({
    selector: 'my-app',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss']
})
export class AppComponent {

    userLoggedIn: boolean;

    constructor(private router: Router) {
    }
}
