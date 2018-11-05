import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HowToEnterComponent } from './how-to-enter.component';

describe('HowToEnterComponent', () => {
  let component: HowToEnterComponent;
  let fixture: ComponentFixture<HowToEnterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HowToEnterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HowToEnterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
