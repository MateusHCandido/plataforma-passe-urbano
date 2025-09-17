import { Component } from '@angular/core';
import { Usuario } from '../../models/usuario.model';
import { UsuarioService } from '../../services/usuario.service';

@Component({
  selector: 'app-excluir-usuario',
  templateUrl: './excluir-usuario.component.html',
  styleUrls: ['./excluir-usuario.component.css']
})
export class ExcluirUsuarioComponent {
  emailBusca = '';
  usuarioEncontrado: Usuario | null = null;
  carregando = false;
  mensagemSucesso = '';
  mensagemErro = '';

  constructor(private usuarioService: UsuarioService) {}

  buscarUsuario() {
    if (!this.emailBusca) {
      return;
    }

    this.carregando = true;
    this.mensagemErro = '';
    this.mensagemSucesso = '';

    this.usuarioService.getAutoConsulta(this.emailBusca).subscribe(
      (usuario) => {
        this.usuarioEncontrado = usuario;
        this.carregando = false;
      },
      (erro) => {
        try {
          const body = erro.json();
          this.mensagemErro = body.mensagem || 'Usuário não encontrado';
        } catch (e) {
          this.mensagemErro = 'Usuário não encontrado';
        }
        this.carregando = false;
      }
    );
  }

  deletarUsuario() {
    if (!this.usuarioEncontrado) return;
  
    // Angular 4 não suporta optional chaining
    const temCartaoAtivo = this.usuarioEncontrado.cartoes && this.usuarioEncontrado.cartoes.some(c => c.statusCartao);
  
    if (temCartaoAtivo) {
      this.mensagemErro = 'Usuário possui cartões ativos. Bloqueie-os antes de excluir.';
      return;
    }
  
    this.carregando = true;
    this.mensagemErro = '';
    this.mensagemSucesso = '';
  
    this.usuarioService.deletarUsuario(this.usuarioEncontrado.email).subscribe(
      () => {
        this.mensagemSucesso = 'Usuário excluído com sucesso!';
        this.usuarioEncontrado = null;
        this.emailBusca = '';
        this.carregando = false;
      },
      erro => {
        try {
          const body = erro.json();
          this.mensagemErro = body.mensagem || 'Erro ao excluir usuário';
        } catch (e) {
          this.mensagemErro = 'Erro ao excluir usuário';
        }
        this.carregando = false;
      }
    );
  }

  fecharModal() {
    this.usuarioEncontrado = null;
    this.mensagemErro = '';
    this.mensagemSucesso = '';
  }  
}
