import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListarUsuariosComponent } from './listar-usuarios/listar-usuarios.component';
import { AdicionarUsuarioComponent } from './adicionar-usuario/adicionar-usuario.component';
import { AlterarUsuarioComponent } from './alterar-usuario/alterar-usuario.component';
import { ExcluirUsuarioComponent } from './excluir-usuario/excluir-usuario.component';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [
    ListarUsuariosComponent, 
    AdicionarUsuarioComponent, 
    AlterarUsuarioComponent, 
    ExcluirUsuarioComponent]
})
export class UsuariosModule { }
