import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LayoutPrincipalComponent } from './layouts/layout-principal/layout-principal.component';
import { LayoutPrincipalRoutingModule } from './layouts/layout-principal/layout-principal-routing.module';
import { FormsModule } from '@angular/forms';

@NgModule({
  imports: [
    CommonModule,
    LayoutPrincipalRoutingModule
  ],
  declarations: [LayoutPrincipalComponent]
})
export class CompartilhadoModule { }
