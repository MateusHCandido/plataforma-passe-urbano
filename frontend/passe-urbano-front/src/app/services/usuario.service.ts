import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response, URLSearchParams } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { EmailPermissaoUsuario, Usuario, UsuarioCadastro } from '../models/usuario.model';
import { AutenticacaoService } from './autenticacao.service';

@Injectable()
export class UsuarioService {
  private apiUrl = 'http://localhost:8765/usuario';

  private apiUrlAuth = 'http://localhost:8000/usuario-autenticado'



  constructor(
    private http: Http,
    private auditLogService: AutenticacaoService
    ) { }

  getAutoConsulta(email: string): Observable<Usuario> {
    const token = localStorage.getItem('access_token');

    const headers = new Headers({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });

    // Adiciona o email como query param
    const params: URLSearchParams = new URLSearchParams();
    params.set('email', email);

    const options = new RequestOptions({ headers: headers, search: params });

    return this.http
      .get(`${this.apiUrl}/auto/consulta`, options)
      .map((res: Response) => res.json() as Usuario);
  }

  consultarUsuarios(): Observable<Usuario[]> {
    const token = localStorage.getItem('access_token');

    // Cria os headers
    const headers = new Headers({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });    

    const options = new RequestOptions({ headers: headers });
    
    return this.http
      .get(`${this.apiUrl}/consultar`, options)
      .map((res: Response) => res.json() as Usuario[]);
  }

  cadastrarUsuario(usuarioCadastro: UsuarioCadastro){
    const token = localStorage.getItem('access_token');

    const headers = new Headers({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });

    const options = new RequestOptions({ headers })

    return this.http
    .post(`${this.apiUrlAuth}/cadastrar`, usuarioCadastro, options)
    .map(res => res.text());
  }

  listarEmailPermissaoUsuario(): Observable<EmailPermissaoUsuario[]>{
    const token = localStorage.getItem('access_token');

    const headers = new Headers({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });

    const options = new RequestOptions({ headers });

    return this.http
    .get(`${this.apiUrlAuth}/listar`, options)
    .map((res: Response) => res.json() as EmailPermissaoUsuario[]);
  }

  alterarUsuario(usuario: { nome: string; email: string; senha?: string }) {
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
      .put(`${this.apiUrl}/alterar`, usuario, options)
      .map(res => res.text()); 
  }

  deletarUsuario(email: string) {
    const token = localStorage.getItem('access_token');
  
    const headers = new Headers({
      'Authorization': 'Bearer ' + token,
      'Content-Type': 'application/json'
    });
  
    const emailUsuario = this.auditLogService.getEmail();

    const params: URLSearchParams = new URLSearchParams();
    params.set('email', email) //email para ser deletado
    params.set('emailUsuario', emailUsuario); //email do usuario operador
    

    const options = new RequestOptions({ headers: headers, search: params });
  
    return this.http
      .delete(this.apiUrl + '/deletar', options)
      .map((res: Response) => res.text());
  }

 
}
