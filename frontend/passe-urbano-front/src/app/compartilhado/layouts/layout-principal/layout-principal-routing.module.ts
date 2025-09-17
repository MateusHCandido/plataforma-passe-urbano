import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PainelHomeComponent } from '../../../painel/painel-home/painel-home.component';

import { LayoutPrincipalComponent } from './layout-principal.component';

const routes: Routes = [
  {
    path: '',
    component: LayoutPrincipalComponent,
    children: [
      { path: 'home', component: PainelHomeComponent },
      { path: '', redirectTo: 'home', pathMatch: 'full' }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LayoutPrincipalRoutingModule {}
