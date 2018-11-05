import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PrizeFeeComponent } from './prize-fee.component';

describe('PrizeFeeComponent', () => {
  let component: PrizeFeeComponent;
  let fixture: ComponentFixture<PrizeFeeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PrizeFeeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PrizeFeeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
