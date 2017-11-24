import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import {ClarityModule} from 'clarity-angular';
import {AppComponent} from './app.component';
import {ROUTING} from './app.routing';
import {HomeComponent} from './home/home.component';
import {AboutComponent} from './about/about.component';
import {UsersListComponent} from './users/users-list/users-list.component';
import {HttpClientModule} from '@angular/common/http';
import {UserService} from './service/UserService';

@NgModule({
    declarations: [
        AppComponent,
        AboutComponent,
        HomeComponent,
        UsersListComponent
    ],
    imports: [
        BrowserAnimationsModule,
        BrowserModule,
        FormsModule,
        HttpModule,
        HttpClientModule,
        ClarityModule,
        ROUTING
    ],
    providers: [UserService],
    bootstrap: [AppComponent]
})
export class AppModule {
}
