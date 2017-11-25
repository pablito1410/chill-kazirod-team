import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {ClarityModule} from 'clarity-angular';
import {AppComponent} from './app.component';
import {ROUTING} from './app.routing';
import {HomeComponent} from './home/home.component';
import {UsersListComponent} from './users/users-list/users-list.component';
import {HttpClientModule} from '@angular/common/http';
import {UserService} from './services/user.service';
import {LoginComponent} from './login/login.component';
import {GamesListComponent} from './games/list/games-list.component';
import {NothingComponent} from './nothing/nothing.component';
import {GameService} from './services/game.service';
import {LoggedUserService} from './services/logged-user.service';
import { RegisterComponent } from './register/register.component';
import { HeaderComponent } from './header/header.component';
import {AuthModule} from './auth/auth.module';
import {HttpModule} from '@angular/http';
import {LoginService} from './services/login.service';
import { InvitationsListComponent } from './invitations-list/invitations-list.component';

@NgModule({
    declarations: [
        AppComponent,
        HomeComponent,
        UsersListComponent,
        LoginComponent,
        GamesListComponent,
        NothingComponent,
        RegisterComponent,
        HeaderComponent,
        InvitationsListComponent
    ],
    imports: [
        BrowserAnimationsModule,
        BrowserModule,
        FormsModule,
        HttpClientModule,
        ClarityModule,
        ROUTING,
        ReactiveFormsModule,
        AuthModule,
        HttpModule
    ],
    providers: [
        UserService,
        GameService,
        LoggedUserService,
        LoginService],
    bootstrap: [AppComponent]
})
export class AppModule {
}
