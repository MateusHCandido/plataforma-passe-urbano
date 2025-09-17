import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { ListarUsuariosComponent } from './listar-usuarios/listar-usuarios.component';
import { AdicionarUsuarioComponent } from './adicionar-usuario/adicionar-usuario.component';
import { ExcluirUsuarioComponent } from './excluir-usuario/excluir-usuario.component';

const routes: Routes = [
  { path: 'usuarios/lista', component: ListarUsuariosComponent },
  { path: 'usuario/adicionar', component: AdicionarUsuarioComponent },
  { path: 'usuario/alterar', component: AdicionarUsuarioComponent },
  { path: 'usuarios/excluir', component: ExcluirUsuarioComponent }
];

@NgModule({
  declarations: [
    ListarUsuariosComponent,
    AdicionarUsuarioComponent
  ],
  imports: [CommonModule, RouterModule.forChild(routes)]
})
export class UsuariosModule {}
