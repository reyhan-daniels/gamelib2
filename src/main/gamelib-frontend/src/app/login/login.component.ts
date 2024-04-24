import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
    username!: string;
    password!: string;

    constructor(private http: HttpClient) {}

    login() {
        const url = 'http://localhost:8080/api/login';
        const headers = new HttpHeaders({ 'Content-Type': 'application/json' })

        this.http.post(url, { username: this.username, password: this.password }, { headers }).subscribe(response => {
            console.log(response);
        }, error => {
            console.error(error);
        });
    }
}
