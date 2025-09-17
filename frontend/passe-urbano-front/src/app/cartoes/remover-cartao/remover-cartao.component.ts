import { Component } from '@angular/core';
import { Cartao } from '../../models/cartao.model';
import { CartaoService } from '../../services/cartao.service';

@Component({
  selector: 'app-remover-cartao',
  templateUrl: './remover-cartao.component.html',
  styleUrls: ['./remover-cartao.component.css']
})
export class RemoverCartaoComponent {
  numeroCartaoBusca: string = '';
  cartaoEncontrado: Cartao | null = null;
  carregando = false;
  mensagemSucesso = '';
  mensagemErro = '';
  confirmacaoExclusao = false;

  constructor(private cartaoService: CartaoService) {}

  buscarCartao() {
    if (!this.numeroCartaoBusca) return;

    this.carregando = true;
    this.mensagemErro = '';
    this.mensagemSucesso = '';

    this.cartaoService.getCartaoPorNumero(+this.numeroCartaoBusca).subscribe(
      (cartao: Cartao) => {
        this.cartaoEncontrado = cartao;
        this.carregando = false;
      },
      (erro) => {
        this.mensagemErro = 'Cartão não encontrado';
        this.carregando = false;
      }
    );
  }

  abrirModalExclusao() {
    if (!this.cartaoEncontrado) return;

    if (this.cartaoEncontrado.statusCartao) {
      this.mensagemErro = 'Cartão ativo não pode ser excluído';
      return;
    }

    this.confirmacaoExclusao = true;
    this.mensagemErro = '';
  }

  confirmarExclusao() {
    if (!this.cartaoEncontrado) return;

    this.carregando = true;
    this.cartaoService.removerCartao(this.cartaoEncontrado.numeroCartao).subscribe(
      () => {
        this.mensagemSucesso = 'Cartão excluído com sucesso!';
        this.cartaoEncontrado = null;
        this.numeroCartaoBusca = '';
        this.confirmacaoExclusao = false;
        this.carregando = false;
      },
      (erro) => {
        this.mensagemErro = 'Erro ao excluir cartão';
        this.carregando = false;
        this.confirmacaoExclusao = false;
      }
    );
  }

  cancelarExclusao() {
    this.confirmacaoExclusao = false;
  }

  fecharModal() {
    this.cartaoEncontrado = null;
    this.mensagemErro = '';
    this.mensagemSucesso = '';
  }
}
