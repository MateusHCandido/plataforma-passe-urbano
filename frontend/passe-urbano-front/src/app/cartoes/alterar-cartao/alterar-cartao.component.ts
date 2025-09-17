import { Component } from '@angular/core';
import { CartaoService } from '../../services/cartao.service';

@Component({
  selector: 'app-alterar-cartao',
  templateUrl: './alterar-cartao.component.html',
  styleUrls: ['./alterar-cartao.component.css']
})
export class AlterarCartaoComponent {

  numeroCartao: string = '';
  carregando = false;
  mensagemErro = '';
  mensagemSucesso = '';

  constructor(private cartaoService: CartaoService) {}

  ativarInativar() {
    if (!this.numeroCartao) return;

    this.carregando = true;
    this.mensagemErro = '';
    this.mensagemSucesso = '';

    this.cartaoService.ativarInativarCartao(Number(this.numeroCartao))
      .subscribe(
        (res: string) => {
          this.carregando = false;
          this.mensagemSucesso = res; // mensagem de sucesso do backend
        },
        (erro) => {
          this.carregando = false;

          try {
            const erroJson = erro.json(); // Angular 4 precisa parsear
            this.mensagemErro = erroJson && erroJson.mensagem 
              ? erroJson.mensagem 
              : 'Erro ao processar requisição';
          } catch (e) {
            this.mensagemErro = 'Erro inesperado no servidor';
          }
        }
      );
  }
}
