import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http'
import { Observable } from 'rxjs'
import { Http, Headers, RequestOptions, Response, URLSearchParams } from '@angular/http';
import { AuditLog } from '../models/logs.model';
declare var require: any;
const jwt_decode = require('jwt-decode'); 

@Injectable()
export class AutenticacaoService {

  private authUrl = 'http://localhost:8000/oauth/token'
  private apiUrl = 'http://localhost:8765/log';
  private clientId = 'urbanape'
  private clientSecret = 'urbanape123'
  
  
  constructor(
    private http: HttpClient,
    private httpLog: Http
    ) { }


  login(email: string, senha: string): Observable<any>{
    const body = new HttpParams()
      .set('grant_type', 'password')
      .set('username', email)
      .set('password', senha);

      const headers = new HttpHeaders({
        'Content-Type': 'application/x-www-form-urlencoded',
        'Authorization': 'Basic ' + btoa(this.clientId + ':' + this.clientSecret)
      });
    
      return this.http.post(this.authUrl, body.toString(), { headers });
  }

  buscarLog(): Observable<AuditLog[]>{
    const emailUsuario = this.getEmail();
    const permissaoUsuario = this.getPermissao();
    
    const token = localStorage.getItem('access_token');

    const headers = new Headers({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });

    const params: URLSearchParams = new URLSearchParams();
    params.set('emailUsuario', emailUsuario);
    params.set('tipoUsuario', permissaoUsuario)

    const options = new RequestOptions({ headers: headers, search: params });

    return this.httpLog
      .get(`${this.apiUrl}/logs`, options)
      .map((res: Response) => res.json() as AuditLog[]);
  }


  getPermissao(): string{
    const decodeToken = jwt_decode(localStorage.getItem('access_token'));
    return decodeToken.authorities[0];
  }

  getEmail(): string{
    const decodeToken = jwt_decode(localStorage.getItem('access_token'));
    const emailUsuarioLogado = decodeToken.user_name;
    return emailUsuarioLogado;
  }

  isAdmin(): boolean{
    const role = this.getPermissao();
    return role === 'ROLE_ADMIN';
  }

  
  
}
