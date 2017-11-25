import { Component, OnInit } from '@angular/core';
import {Invitation} from '../data/invitation';
import {InvitationsService} from '../services/invitations.service';
import {LoggedUserService} from '../services/logged-user.service';

@Component({
  selector: 'app-invitations-list',
  templateUrl: './invitations-list.component.html',
  styleUrls: ['./invitations-list.component.css']
})
export class InvitationsListComponent implements OnInit {

  constructor(protected invService: InvitationsService,
              protected loggedUserService: LoggedUserService) { }

  invitations: Invitation[] = [];

  ngOnInit() {
      this.invService.getInvitations(this.loggedUserService.userId)
          .subscribe( (val: Invitation[]) => {
              this.invitations = val;
          })
  }

  onReject(){
      // Change status to canceled
  }

  onAccept(){
      // Change status to accepted
  }

}
