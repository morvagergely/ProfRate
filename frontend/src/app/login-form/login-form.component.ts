import { Input, Component, Output, EventEmitter, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.scss']
})
export class LoginFormComponent {

  constructor(private authService: AuthService, private router: Router) {}

  form: FormGroup = new FormGroup({
    username: new FormControl(''),
    password: new FormControl(''),
  });

  submit() {
    console.log('submitted');
    this.error = null;
    if (this.form.valid) {
      console.log('valid');
      this.authService.login(this.form.value.username, this.form.value.password).subscribe({
        next: () => this.router.navigate(['/courses']),
        error: (status: number) => {
          if (status === 401) {
            this.error = 'Error: username or password is invalid.';
          }
          else {
            this.error = `An unknown error has occured. Please try again later. Code ${status}.`;
          }
        },
      });
      this.submitEM.emit(this.form.value);
    }
  }

  goto(page: string) {
    this.router.navigate([page]);
  }

  @Input() error: string | null;

  @Output() submitEM = new EventEmitter();

}
