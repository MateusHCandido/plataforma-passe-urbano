import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PainelHomeComponent } from './painel-home.component';

describe('PainelHomeComponent', () => {
  let component: PainelHomeComponent;
  let fixture: ComponentFixture<PainelHomeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PainelHomeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PainelHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
