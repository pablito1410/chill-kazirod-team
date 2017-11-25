import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthHttp} from 'angular2-jwt';
import {LoginService} from '../services/login.service';
import {Router} from '@angular/router';
import {LoggedUserService} from '../services/logged-user.service';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

    form: FormGroup;

    constructor(
        private fb: FormBuilder,
        private loginService: LoginService,
        private router: Router,
        private loggedUserService: LoggedUserService) {
    }

    ngOnInit() {
        this.form = this.fb.group({
            username: ['', Validators.required],
            password: ['', Validators.required]
        })
    }

    onSubmit(){
       if (this.form.valid){
           this.loginService.login(this.form.getRawValue())
               .subscribe( val => {
                   // Add to logged user service;
                   this.router.navigate(['/about']);
               })
       }
    }

}
