import { Cartao } from "./cartao.model";

export interface Usuario {
    usuarioId: number;
    nome: string;
    email: string;
    cartoes?: Cartao[];
}

export interface EmailPermissaoUsuario {
    email: string;
    permissao:string;
}

export interface UsuarioCadastro {
    nome: string;
    email: string;
    senha: string;
    permissao: string;
}