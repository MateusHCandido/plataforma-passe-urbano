export type TipoCartao = 'COMUM' | 'ESTUDANTE' | 'TRABALHADOR';

export interface Cartao {
  cartaoId: number;
  numeroCartao: number;
  nome: string;
  tipoCartao: TipoCartao;
  statusCartao: boolean;
}

export interface CartaoCadastro {
  emailUsuario: string;
  tipoCartao: string;
}