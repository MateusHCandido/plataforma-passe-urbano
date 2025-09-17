import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { CartaoService } from '../../services/cartao.service';
import { CartaoCadastro } from '../../models/cartao.model';

@Component({
  selector: 'app-adicionar-cartao',
  templateUrl: './adicionar-cartao.component.html',
  styleUrls: ['./adicionar-cartao.component.css']
})
export class AdicionarCartaoComponent {
  cartao: CartaoCadastro = { emailUsuario: '', tipoCartao: '' };
  carregando = false;
  mensagemSucesso = '';
  mensagemErro = '';

  tiposCartao: string[] = ['COMUM', 'ESTUDANTE', 'TRABALHADOR'];

  constructor(private cartaoService: CartaoService) {}

  adicionarCartao(formulario: NgForm) {
    if (formulario.invalid) return;

    this.carregando = true;
    this.mensagemErro = '';
    this.mensagemSucesso = '';

    this.cartaoService.adicionarCartao(this.cartao)
      .subscribe(
        (res: any) => {
          this.carregando = false;
          this.mensagemSucesso = 'Cartão adicionado com sucesso!';
          formulario.resetForm();
          this.cartao.tipoCartao = '';
        },
        (erro) => {
          try {
            const body = erro.json();
            this.mensagemErro = body.mensagem || 'Erro ao adicionar cartão';
          } catch (e) {
            this.mensagemErro = 'Erro ao adicionar cartão';
          }
          this.carregando = false;
        }
      );
  }
}
