import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { LoginComponent } from './acesso/login/login.component';
import { FormsModule } from '@angular/forms';
import { AutenticacaoService } from './services/autenticacao.service';
import { HttpClientModule } from '@angular/common/http';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [AutenticacaoService],
  bootstrap: [AppComponent]
})
export class AppModule { }
