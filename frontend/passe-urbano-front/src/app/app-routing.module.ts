import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './acesso/login/login.component';
import { AdicionarCartaoComponent } from './cartoes/adicionar-cartao/adicionar-cartao.component';
import { AlterarCartaoComponent } from './cartoes/alterar-cartao/alterar-cartao.component';
import { ListarCartoesComponent } from './cartoes/listar-cartoes/listar-cartoes.component';
import { RemoverCartaoComponent } from './cartoes/remover-cartao/remover-cartao.component';
import { LayoutPrincipalComponent } from './compartilhado/layouts/layout-principal/layout-principal.component';
import { AuditoriaComponent } from './painel/auditoria/auditoria.component';
import { PainelHomeComponent } from './painel/painel-home/painel-home.component';
import { AdicionarUsuarioComponent } from './usuarios/adicionar-usuario/adicionar-usuario.component';
import { AlterarUsuarioComponent } from './usuarios/alterar-usuario/alterar-usuario.component';
import { ExcluirUsuarioComponent } from './usuarios/excluir-usuario/excluir-usuario.component';
import { ListarUsuariosComponent } from './usuarios/listar-usuarios/listar-usuarios.component';



const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { 
    path: '', 
    component: LayoutPrincipalComponent,
    children: [
      { path: '', redirectTo: 'home', pathMatch: 'full' },
      { path: 'home', component: PainelHomeComponent },
      { path: 'usuarios/lista', component: ListarUsuariosComponent },
      { path: 'usuarios/adicionar', component: AdicionarUsuarioComponent },
      { path: 'usuarios/alterar', component: AlterarUsuarioComponent },
      { path: 'usuarios/excluir', component: ExcluirUsuarioComponent },
      { path: 'cartoes/adicionar', component: AdicionarCartaoComponent },
      { path: 'cartoes/excluir', component: RemoverCartaoComponent },
      { path: 'cartoes/lista',component: ListarCartoesComponent },
      { path: 'cartoes/alterar', component: AlterarCartaoComponent },
      { path: 'config/logs', component: AuditoriaComponent }
      
    ]
  },
  { path: '**', redirectTo: '' } 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
