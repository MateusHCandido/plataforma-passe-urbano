import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { AdicionarCartaoComponent } from './adicionar-cartao/adicionar-cartao.component';
import { RemoverCartaoComponent } from './remover-cartao/remover-cartao.component';
import { ListarCartoesComponent } from './listar-cartoes/listar-cartoes.component';
import { AlterarCartaoComponent } from './alterar-cartao/alterar-cartao.component';


const routes: Routes = [
  { path: 'cartoes/adicionar', component: AdicionarCartaoComponent },
  { path: 'cartoes/remover', component: RemoverCartaoComponent },
  { path: 'cartoes/lista', component: ListarCartoesComponent },
  { path: 'cartoes/alterar', component: AlterarCartaoComponent }
];

@NgModule({
  declarations: [
    AdicionarCartaoComponent
  ],
  imports: [CommonModule, RouterModule.forChild(routes)]
})
export class CartaoModule {}