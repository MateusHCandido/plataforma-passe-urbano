export interface AuditLog {
    usuarioEmail: string;
    acao: string;
    entidade: string;
    entidadeId: number;
    timestamp: string; 
    detalhe: string;
  }