import { Component, OnInit } from '@angular/core';
import { Cartao } from '../../models/cartao.model';
import { CartaoService } from '../../services/cartao.service';

@Component({
  selector: 'app-listar-cartoes',
  templateUrl: './listar-cartoes.component.html',
  styleUrls: ['./listar-cartoes.component.css']
})
export class ListarCartoesComponent implements OnInit {
  cartoes: Cartao[] = [];
  cartoesFiltrados: Cartao[] = [];
  filtro: string = '';
  cartaoSelecionado: Cartao | null = null;

  // Paginação
  paginaAtual: number = 1;
  itensPorPagina: number = 10;
  paginas: number[] = [];

  constructor(private cartaoService: CartaoService) {}

  ngOnInit() {
    this.carregarCartoes();
  }

  carregarCartoes() {
    this.cartaoService.listarCartoes().subscribe(
      (cartoes: Cartao[]) => {
        this.cartoes = cartoes;
        this.cartoesFiltrados = cartoes;
        this.calcularPaginas();
      },
      (erro) => {
        console.error('Erro ao carregar cartões', erro);
      }
    );
  }

  filtrarCartoes() {
    const termo = this.filtro.toLowerCase();
    this.cartoesFiltrados = this.cartoes.filter(cartao =>
      cartao.numeroCartao.toString().includes(termo) ||
      cartao.nome.toLowerCase().includes(termo) ||
      cartao.tipoCartao.toLowerCase().includes(termo)
    );
    this.paginaAtual = 1;
    this.calcularPaginas();
  }

  calcularPaginas() {
    const totalPaginas = Math.ceil(this.cartoesFiltrados.length / this.itensPorPagina);
    this.paginas = Array.from({ length: totalPaginas }, (_, i) => i + 1);
  }

  get cartoesPaginados() {
    const inicio = (this.paginaAtual - 1) * this.itensPorPagina;
    return this.cartoesFiltrados.slice(inicio, inicio + this.itensPorPagina);
  }

  mudarPagina(pagina: number) {
    this.paginaAtual = pagina;
  }

  abrirModal(cartao: Cartao) {
    this.cartaoSelecionado = cartao;
  }

  fecharModal() {
    this.cartaoSelecionado = null;
  }
}
