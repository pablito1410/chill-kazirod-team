import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {Http, RequestOptions} from '@angular/http';
import {AUTH_PROVIDERS, AuthConfig, AuthHttp} from 'angular2-jwt';

export function authHttpServiceFactory(http: Http, options: RequestOptions) {
    return new AuthHttp(new AuthConfig(), http, options);
}

@NgModule({
    providers: [
        {
            provide: AuthHttp,
            useFactory: authHttpServiceFactory,
            deps: [Http, RequestOptions]
        }, AUTH_PROVIDERS
    ]
})
export class AuthModule { }
