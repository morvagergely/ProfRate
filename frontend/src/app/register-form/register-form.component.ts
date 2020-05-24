import { Input, Component, Output, EventEmitter, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.scss']
})
export class RegisterFormComponent {

  constructor(
    private authService: AuthService,
    private router: Router,
    private snackBar: MatSnackBar
  ) { }

  form: FormGroup = new FormGroup({
    username: new FormControl('', [
      Validators.required,
    ]),
    password: new FormControl('', [
      Validators.required,
    ]),
    confirmPassword: new FormControl('', [
      Validators.required,
    ]),
  });

  submit() {
    this.error = null;
    if (!this.form.get('username').valid) {
      this.error = 'Error: Email invalid';
      return;
    }
    if (this.form.value.password != this.form.value.confirmPassword) {
      this.error = 'Error: Passwords do not match!'
      return;
    }
    this.authService.register(this.form.value.username, this.form.value.password).subscribe({
      next: () => {
        this.router.navigate(['/login']);
        this.snackBar.open("Nice registartion, yo!", "OK", { duration: 5000, verticalPosition: 'top' });
      },
      error: (status: number) => {
        if (status === 400) {
          this.error = 'Error: Username already exists!';
        }
        else {
          this.error = `An unknown error has occured. Please try again later. Code ${status}.`;
        }
      },
    });
  }

  navigateToLogin() {
    this.router.navigate(['/login']);
  }

  @Input() error: string | null;

  @Output() submitEM = new EventEmitter();

}
