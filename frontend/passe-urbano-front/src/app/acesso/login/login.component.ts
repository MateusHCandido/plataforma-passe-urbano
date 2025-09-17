import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AutenticacaoService } from '../../services/autenticacao.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  email: string = '';
  senha: string = '';
  

  constructor(
    private authService: AutenticacaoService,
    private router: Router
  ) {}

  
  autenticar() {
    if (!this.email || !this.senha) {
      console.error('Email e senha são obrigatórios');
      return;
    }

    this.authService.login(this.email, this.senha).subscribe({
      next: (response) => {
        if (!response.access_token) {
          console.error('Token não retornado pelo backend');
          return;
        }

        localStorage.setItem('access_token', response.access_token);

        this.router.navigate(['/home']);
      },
      error: (err) => {
        console.error('Erro ao autenticar usuário', err);
        alert('Email ou senha inválidos!');
      }
    });
  }

 

}
