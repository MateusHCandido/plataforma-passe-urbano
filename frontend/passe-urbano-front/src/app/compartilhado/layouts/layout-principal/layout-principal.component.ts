import { Component, OnInit } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { Usuario } from '../../../models/usuario.model';
import { UsuarioService } from '../../../services/usuario.service';
import { filter } from 'rxjs/operators';
import { AutenticacaoService } from '../../../services/autenticacao.service';

declare var require: any;
const jwt_decode = require('jwt-decode'); 

@Component({
  selector: 'app-layout-principal',
  templateUrl: './layout-principal.component.html',
  styleUrls: ['./layout-principal.component.css']
})
export class LayoutPrincipalComponent implements OnInit {

  usuario: Usuario = null;


  titulo = 'Bem-vindo';
  configMenuOpen: boolean = false;
  sidebarOpen = false;
  usuariosMenuOpen = false;
  cartoesMenuOpen = false;

  constructor(
    private router: Router,
    private usuarioService: UsuarioService,
    private authService: AutenticacaoService
  ) {}

  ngOnInit() {
    this.router.events
      .pipe(filter(event => event instanceof NavigationEnd))
      .subscribe((event: NavigationEnd) => {
        this.definirTitulo(event.urlAfterRedirects); // garante pegar a URL final
    });

    console.log(this.authService.getPermissao());

    const token = localStorage.getItem('access_token');

    if (token) {
      const payload: any = jwt_decode(token); 

      const email = payload && payload.user_name ? payload.user_name : null;

      if (email) {
        this.usuarioService.getAutoConsulta(email).subscribe(
          (usuario: Usuario) => {
            this.usuario = usuario;
          },
          (err) => {
            console.error('Erro ao buscar usuário', err);
          }
        );
      }
    } else {
      console.error('Token não encontrado no localStorage');
    }
  }

  definirTitulo(url: string) {
    if (url.includes('/home')) {
      this.titulo = `Bem-vindo`;
    } else if (url.includes('/usuarios/lista')) {
      this.titulo = 'Lista de Usuários';
    } else if (url.includes('/usuarios/adicionar')) {
      this.titulo = 'Adicionar Usuário';
    } else if (url.includes('/usuarios/alterar')) {
      this.titulo = 'Alterar Usuário';
    } else if (url.includes('/usuarios/excluir')) {
      this.titulo = 'Excluir Usuário';
    } else if (url.includes('/cartoes')) {
      this.titulo = 'Gerenciamento de Cartões';
    } else {
      this.titulo = 'Painel';
    }
  }

  toggleConfigMenu(event: Event): void {
    event.preventDefault();
    this.configMenuOpen = !this.configMenuOpen;
  }

  toggleSidebar() {
    this.sidebarOpen = !this.sidebarOpen;
  }

  toggleUsuariosMenu() {
    event.preventDefault();
    this.usuariosMenuOpen = !this.usuariosMenuOpen;
  }

  toggleCartoesMenu(event: Event) {
    event.preventDefault(); 
    this.cartoesMenuOpen = !this.cartoesMenuOpen;
  }

  logout() {
    localStorage.clear();
    this.router.navigate(['/login']);
  }
}
