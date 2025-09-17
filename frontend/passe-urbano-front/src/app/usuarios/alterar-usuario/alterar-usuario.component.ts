import { Component, Input} from '@angular/core';
import { NgForm } from '@angular/forms';
import { Usuario } from '../../models/usuario.model';
import { UsuarioService } from '../../services/usuario.service';

@Component({
  selector: 'app-alterar-usuario',
  templateUrl: './alterar-usuario.component.html',
  styleUrls: ['./alterar-usuario.component.css']
})
export class AlterarUsuarioComponent {

  emailBusca = '';
  usuarioEncontrado: Usuario | null = null;

  novaSenha = ''; 

  carregando = false;
  mensagemSucesso = '';
  mensagemErro = '';

  constructor(private usuarioService: UsuarioService) {}

  buscarUsuario() {
    if (!this.emailBusca) return;

    this.carregando = true;
    this.mensagemErro = '';
    this.usuarioEncontrado = null;
    this.novaSenha = '';

    this.usuarioService.getAutoConsulta(this.emailBusca)
      .subscribe(
        (usuario: Usuario) => {
          this.carregando = false;
          this.usuarioEncontrado = usuario;
        },
        (erro) => {
          this.carregando = false;
          this.mensagemErro = 'Usuário não encontrado';
        }
      );
  }

  alterarUsuario(formulario: NgForm) {
    if (!this.usuarioEncontrado || formulario.invalid) return;

    this.carregando = true;
    this.mensagemSucesso = '';
    this.mensagemErro = '';

    const dadosAlteracao = {
      nome: this.usuarioEncontrado.nome,
      email: this.usuarioEncontrado.email,
      senha: this.novaSenha || undefined
    };

    this.usuarioService.alterarUsuario(dadosAlteracao)
      .subscribe(
        (res: string) => {
          this.carregando = false;
          this.mensagemSucesso = res//'Alteração em usuário realizada com sucesso'
          
          setTimeout(() => {
            formulario.resetForm();
            this.usuarioEncontrado = null;
            this.emailBusca = '';
            this.novaSenha = '';
            this.mensagemSucesso = '';
          }, 2000); 
          
        },
        (erro) => {
          this.carregando = false;
          this.mensagemErro = 'Erro ao alterar usuário';
        }
      );
  }

}
