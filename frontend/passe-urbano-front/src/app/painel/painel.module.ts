import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PainelRoutingModule } from './painel-routing.module';
import { PainelHomeComponent } from './components/painel-home/painel-home.component';

@NgModule({
  imports: [
    CommonModule,
    PainelRoutingModule
  ],
  declarations: [PainelHomeComponent]
})
export class PainelModule { }
