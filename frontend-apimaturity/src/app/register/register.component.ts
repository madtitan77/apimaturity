import { Component ,OnInit} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http'; 

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registerForm!: FormGroup;

  constructor(private fb: FormBuilder, private http: HttpClient) { }
  

  ngOnInit(): void {
    this.registerForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]],
      verifyPassword: ['', Validators.required]
    }, { validator: this.checkPasswords });
  }

  checkPasswords(group: FormGroup) { // here we have the 'passwords' group
    let pass = group.get('password')?.value;
    let confirmPass = group.get('verifyPassword')?.value;

    return pass === confirmPass ? null : { passwordMismatch: true };
  }

  onSubmit(): void {
    if (this.registerForm.valid) {
      const payload = {
        email: this.registerForm.value.email,
        password: this.registerForm.value.password
      };
      this.http.post<any>('http://localhost:8080/api/users/create', payload) // Specify <any> or a more specific type
        .subscribe({
          next: (response: any) => { // Specify the type of response
            console.log('Registration successful', response);
          },
          error: (error: any) => { // Specify the type of error
            console.error('Registration failed', error);
          }
        });
    }
  }
  
}