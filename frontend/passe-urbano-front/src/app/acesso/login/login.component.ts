import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AutenticacaoService } from '../../services/autenticacao.service';
import * as jwt_decode from 'jwt-decode';

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
    this.authService.login(this.email, this.senha).subscribe({
      next: (response) => {
        // Salva token no localStorage
        localStorage.setItem('access_token', response.access_token);

        // Decodifica o token e salva os dados do usuário
        const usuario: any = (jwt_decode as any)(response.access_token);
        localStorage.setItem('usuario', JSON.stringify(usuario));

        // Redireciona para o painel
        this.router.navigate(['/painel']);
      },
      error: (err) => {
        console.error('Erro ao autenticar usuário', err);
      }
    });
  }
}
