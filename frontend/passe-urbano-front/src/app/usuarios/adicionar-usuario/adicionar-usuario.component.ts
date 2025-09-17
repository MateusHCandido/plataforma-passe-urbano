import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { UsuarioCadastro } from '../../models/usuario.model';
import { UsuarioService } from '../../services/usuario.service';

@Component({
  selector: 'app-adicionar-usuario',
  templateUrl: './adicionar-usuario.component.html',
  styleUrls: ['./adicionar-usuario.component.css']
})
export class AdicionarUsuarioComponent{

  usuario: UsuarioCadastro = {
    nome: '',
    email: '',
    senha: '',
    permissao: ''
  };

  carregando: boolean = false;
  mensagemSucesso = '';
  mensagemErro = '';

  constructor(
    private usuarioService: UsuarioService,
    private router: Router 
  ) { }

  
  adicionarUsuario(formulario: NgForm){
    if (formulario.invalid) return;
    this.carregando = true;
    this.mensagemSucesso = '';
    this.mensagemErro = '';

    this.usuarioService.cadastrarUsuario(this.usuario)
      .subscribe(
        (response: string) => {
          this.carregando = false;
          this.mensagemSucesso = response;
          formulario.resetForm()
        },
        (erro) => {
          this.carregando = false;
          this.mensagemErro = 'Email já cadastrado';
        }
      );
  }


}
