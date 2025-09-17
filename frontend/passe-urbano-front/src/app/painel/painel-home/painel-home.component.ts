import { Component, OnInit } from '@angular/core';
import { CartaoService } from '../../services/cartao.service';
import { UsuarioService } from '../../services/usuario.service';
import { AutenticacaoService } from '../../services/autenticacao.service';
import { Cartao, TipoCartao } from '../../models/cartao.model';
import { EmailPermissaoUsuario } from '../../models/usuario.model';

@Component({
  selector: 'app-painel-home',
  templateUrl: './painel-home.component.html',
  styleUrls: ['./painel-home.component.css']
})
export class PainelHomeComponent implements OnInit {

  cartoes: Cartao[] = [];
  totalUsuarios = 0;
  totalCartoesAtivos = 0;
  totalCartoesInativos = 0;

  // Gráficos
  cartoesPorTipoLabels: TipoCartao[] = ['COMUM', 'ESTUDANTE', 'TRABALHADOR'];
  cartoesPorTipoData: number[] = [0, 0, 0];
  cartoesPorTipoColors = [{ backgroundColor: ['#00acc9', '#0097a7', '#d32f2f'] }];

  cartoesAtivosInativosLabels = ['Ativos', 'Inativos'];
  cartoesAtivosInativosData: number[] = [0, 0];
  cartoesAtivosInativosColors = [{ backgroundColor: ['#00acc9', '#d32f2f'] }];

  usuariosPorTipoLabels: string[] = ['ADMIN', 'USER'];
  usuariosPorTipoData: number[] = [0, 0];
  usuariosPorTipoColors = [{ backgroundColor: ['#00acc9', '#d32f2f'] }];

  constructor(
    private cartaoService: CartaoService,
    private usuarioService: UsuarioService,
    private authService: AutenticacaoService
  ) {}

  ngOnInit(): void {
    this.loadDados();
  }

  loadDados(): void {
    // Listar cartões
    this.cartaoService.listarCartoes().subscribe(cartoes => {
      this.cartoes = cartoes || [];
      this.processarCartoes();
    });

    // Listar usuários e contar por permissão
    this.usuarioService.listarEmailPermissaoUsuario().subscribe((usuarios: EmailPermissaoUsuario[]) => {
      console.log(usuarios);
      if (!usuarios) return;

      this.totalUsuarios = usuarios.length;

      const distribuicao: { [key: string]: number } = { ADMIN: 0, USER: 0 };
      usuarios.forEach(u => {
        if (u.permissao === 'ADMIN') distribuicao.ADMIN += 1;
        else distribuicao.USER += 1;
      });

      this.usuariosPorTipoData = [distribuicao.ADMIN, distribuicao.USER];
    });
  }

  private processarCartoes(): void {
    if (!this.cartoes || this.cartoes.length === 0) {
      this.cartoesPorTipoData = [0, 0, 0];
      this.cartoesAtivosInativosData = [0, 0];
      return;
    }

    this.totalCartoesAtivos = this.cartoes.filter(c => c.statusCartao).length;
    this.totalCartoesInativos = this.cartoes.filter(c => !c.statusCartao).length;

    const distribuicaoTipo: Record<TipoCartao, number> = { COMUM: 0, ESTUDANTE: 0, TRABALHADOR: 0 };
    this.cartoes.forEach(c => {
      if (c.tipoCartao && distribuicaoTipo.hasOwnProperty(c.tipoCartao)) {
        distribuicaoTipo[c.tipoCartao] += 1;
      }
    });

    this.cartoesPorTipoData = this.cartoesPorTipoLabels.map(label => distribuicaoTipo[label]);
    this.cartoesAtivosInativosData = [this.totalCartoesAtivos, this.totalCartoesInativos];
  }
}
