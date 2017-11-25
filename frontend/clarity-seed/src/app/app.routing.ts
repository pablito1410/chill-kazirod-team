import { ModuleWithProviders } from '@angular/core/src/metadata/ng_module';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import {UsersListComponent} from './users/users-list/users-list.component';
import {GamesListComponent} from './games/list/games-list.component';
import {NothingComponent} from './nothing/nothing.component';
import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './register/register.component';
import {InvitationsListComponent} from './invitations-list/invitations-list.component';


export const ROUTES: Routes = [
    {path: '', redirectTo: 'home', pathMatch: 'full'},
    {path: 'home', component: HomeComponent},
    {path: 'users', component: UsersListComponent},
    {path: 'games', component: GamesListComponent},
    {path: 'login', component: LoginComponent},
    {path: 'register', component: RegisterComponent},
    {path: 'invitations', component: InvitationsListComponent},
    {path: '**', component: NothingComponent }
];

export const ROUTING: ModuleWithProviders = RouterModule.forRoot(ROUTES);
