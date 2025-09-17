import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuditoriaComponent } from './auditoria/auditoria.component';
import { PainelHomeComponent } from './painel-home/painel-home.component';

const routes: Routes = [
  { path: '', component: PainelHomeComponent },
  { path: 'logs', component: AuditoriaComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PainelRoutingModule { }
