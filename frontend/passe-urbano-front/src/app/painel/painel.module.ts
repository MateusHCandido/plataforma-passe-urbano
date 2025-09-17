import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PainelHomeComponent } from './painel-home/painel-home.component';
import { PainelRoutingModule } from './painel-routing.module';
import { FormsModule } from '@angular/forms';
import { AuditoriaComponent } from './auditoria/auditoria.component';


@NgModule({
  imports: [
    CommonModule,
    PainelRoutingModule
  ],
  declarations: [PainelHomeComponent, AuditoriaComponent]
})
export class PainelModule { }
