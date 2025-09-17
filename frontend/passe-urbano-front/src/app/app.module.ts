import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { LoginComponent } from './acesso/login/login.component';
import { FormsModule } from '@angular/forms';
import { AutenticacaoService } from './services/autenticacao.service';
import { HttpClientModule } from '@angular/common/http';
import { LayoutPrincipalComponent } from './compartilhado/layouts/layout-principal/layout-principal.component';
import { RouterModule } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { PainelHomeComponent } from './painel/painel-home/painel-home.component';
import { UsuarioService } from './services/usuario.service';
import { HttpModule } from '@angular/http';
import { ListarUsuariosComponent } from './usuarios/listar-usuarios/listar-usuarios.component';
import { AdicionarUsuarioComponent } from './usuarios/adicionar-usuario/adicionar-usuario.component';
import { AlterarUsuarioComponent } from './usuarios/alterar-usuario/alterar-usuario.component';
import { ExcluirUsuarioComponent } from './usuarios/excluir-usuario/excluir-usuario.component';
import { AdicionarCartaoComponent } from './cartoes/adicionar-cartao/adicionar-cartao.component';
import { CartaoService } from './services/cartao.service';
import { RemoverCartaoComponent } from './cartoes/remover-cartao/remover-cartao.component';
import { ListarCartoesComponent } from './cartoes/listar-cartoes/listar-cartoes.component';
import { AlterarCartaoComponent } from './cartoes/alterar-cartao/alterar-cartao.component';
import { ChartsModule } from 'ng2-charts';
import { AuditoriaComponent } from './painel/auditoria/auditoria.component';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    LayoutPrincipalComponent,
    PainelHomeComponent,
    ListarUsuariosComponent,
    AdicionarUsuarioComponent,
    AlterarUsuarioComponent,
    ExcluirUsuarioComponent,
    AdicionarCartaoComponent,
    RemoverCartaoComponent,
    ListarCartoesComponent,
    AlterarCartaoComponent,
    AuditoriaComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule,
    AppRoutingModule,
    HttpModule,
    ChartsModule
  ],
  providers: [
    AutenticacaoService, 
    UsuarioService,
    CartaoService    
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
