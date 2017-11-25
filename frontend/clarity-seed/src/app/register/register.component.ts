import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {UserService} from '../services/user.service';
import {User} from '../data/user';

@Component({
    selector: 'app-register',
    templateUrl: './register.component.html',
    styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

    registerForm: FormGroup;

    constructor(protected fb: FormBuilder,
                protected userService: UserService) {
    }

    ngOnInit() {
        this.registerForm = this.fb.group({
            userName: ['', Validators.required],
            password: ['', Validators.required],
            firstName: [''],
            lastName: ['']
        })
    }

    onSubmit() {
        if (this.registerForm.valid){
            this.userService.post(this.registerForm.getRawValue())
                .subscribe(val => this.readResponse(val));
        }
    }

    readResponse(val: any){
        JSON.stringify(val);
    }
}
