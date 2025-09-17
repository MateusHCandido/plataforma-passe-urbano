import { Component, OnInit } from '@angular/core';
import { AuditLog } from '../../models/logs.model';
import { AutenticacaoService } from '../../services/autenticacao.service';


@Component({
  selector: 'app-auditoria',
  templateUrl: './auditoria.component.html',
  styleUrls: ['./auditoria.component.css']
})
export class AuditoriaComponent implements OnInit {
  logs: AuditLog[] = [];
  logsFiltrados: AuditLog[] = [];
  logsPaginados: AuditLog[] = [];
  filtro = '';
  paginaAtual = 1;
  itensPorPagina = 5;
  totalPaginas = 1;
  paginas: number[] = [];
  loading = false;
  error = '';

  constructor(private logService: AutenticacaoService) {}

  ngOnInit(): void {
    this.buscarLogs();
  }

  buscarLogs(): void {
    this.loading = true;
    this.logService.buscarLog().subscribe({
      next: (data) => {
        this.logs = data;
        this.logsFiltrados = [...this.logs];
        this.atualizarPaginacao();
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Erro ao carregar logs';
        console.error(err);
        this.loading = false;
      }
    });
  }

  filtrarLogs(): void {
    const termo = this.filtro.toLowerCase();
    this.logsFiltrados = this.logs.filter(
      log =>
        log.usuarioEmail.toLowerCase().includes(termo) ||
        log.acao.toLowerCase().includes(termo) ||
        log.entidade.toLowerCase().includes(termo)
    );
    this.paginaAtual = 1;
    this.atualizarPaginacao();
  }

  atualizarPaginacao(): void {
    this.totalPaginas = Math.ceil(this.logsFiltrados.length / this.itensPorPagina);
    this.paginas = Array.from({ length: this.totalPaginas }, (_, i) => i + 1);

    const inicio = (this.paginaAtual - 1) * this.itensPorPagina;
    const fim = inicio + this.itensPorPagina;
    this.logsPaginados = this.logsFiltrados.slice(inicio, fim);
  }

  mudarPagina(pagina: number): void {
    this.paginaAtual = pagina;
    this.atualizarPaginacao();
  }
}
