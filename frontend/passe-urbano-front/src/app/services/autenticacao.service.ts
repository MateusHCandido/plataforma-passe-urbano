import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http'
import { Observable } from 'rxjs'
import { map } from 'rxjs/operators';

@Injectable()
export class AutenticacaoService {

  private authUrl = 'http://localhost:8000/oauth/token'
  private clientId = 'urbanape'
  private clientSecret = 'urbanape123'
  
  
  constructor(private http: HttpClient) { }


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

  
}
