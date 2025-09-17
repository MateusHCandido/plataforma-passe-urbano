import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response, URLSearchParams } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { Cartao, CartaoCadastro } from '../models/cartao.model';
import { AutenticacaoService } from './autenticacao.service';


@Injectable()
export class CartaoService {

  private apiUrl = 'http://localhost:8765/cartao'


  constructor(
    private http: Http,
    private auditLogService: AutenticacaoService
    ) { }


  getCartaoPorNumero(numeroCartao:number): Observable<Cartao>{
    const token = localStorage.getItem('access_token');

    const headers = new Headers({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });

    const options = new RequestOptions({ headers });

    return this.http
      .get(`${this.apiUrl}/buscar/${numeroCartao}`, options)
      .map((res: Response) => res.json() as Cartao);
  }

  adicionarCartao(cartaoCadastro: CartaoCadastro){
    const token = localStorage.getItem('access_token');

    const headers = new Headers({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });

    const emailUsuario = this.auditLogService.getEmail();

    const params: URLSearchParams = new URLSearchParams();
    params.set('emailUsuario', emailUsuario);
    
  
    const options = new RequestOptions({ headers: headers, search: params });
  
    return this.http
      .post(`${this.apiUrl}/adicionar`, cartaoCadastro, options)
      .map(res => res.text());
  }

  listarCartoes(): Observable<Cartao[]> {
    const token = localStorage.getItem('access_token');

    const headers = new Headers({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });

    const options = new RequestOptions({ headers });

    return this.http
      .get(`${this.apiUrl}/listar`, options)
      .map((res: Response) => res.json() as Cartao[]);
  }

  removerCartao(numeroCartao:number){
    const token = localStorage.getItem('access_token');

    const headers = new Headers({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });

    const emailUsuario = this.auditLogService.getEmail();

    const params: URLSearchParams = new URLSearchParams();
    params.set('emailUsuario', emailUsuario);
    
  
    const options = new RequestOptions({ headers: headers, search: params });
  
    return this.http
      .delete(`${this.apiUrl}/remover/${numeroCartao}`, options)
      .map(res => res.text());
  }

  ativarInativarCartao(numeroCartao: number) {
    const token = localStorage.getItem('access_token');
  
    const headers = new Headers({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });

    const emailUsuario = this.auditLogService.getEmail();

    const params: URLSearchParams = new URLSearchParams();
    params.set('emailUsuario', emailUsuario);
    
  
    const options = new RequestOptions({ headers: headers, search: params });
  
    return this.http
      .put(`${this.apiUrl}/ativar/inativar/${numeroCartao}`, {}, options) 
      .map(res => res.text());
    
  }

  
}
