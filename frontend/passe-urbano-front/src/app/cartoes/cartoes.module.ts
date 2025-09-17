import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdicionarCartaoComponent } from './adicionar-cartao/adicionar-cartao.component';
import { RemoverCartaoComponent } from './remover-cartao/remover-cartao.component';
import { ListarCartoesComponent } from './listar-cartoes/listar-cartoes.component';
import { AlterarCartaoComponent } from './alterar-cartao/alterar-cartao.component';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [
    AdicionarCartaoComponent,
    RemoverCartaoComponent,
    ListarCartoesComponent,
    AlterarCartaoComponent
  ]
})
export class CartoesModule { }
