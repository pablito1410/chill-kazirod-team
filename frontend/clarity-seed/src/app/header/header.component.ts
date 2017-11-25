import { Component, OnInit } from '@angular/core';
import {LoggedUserService} from '../services/logged-user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(protected loggedUserService: LoggedUserService) { }

  userLoggedIn: boolean = false;

  ngOnInit() {
      if (this.loggedUserService.userId != null){
          this.userLoggedIn = true;
      }
  }

}
