import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PartenersComponent } from './parteners.component';

describe('PartenersComponent', () => {
  let component: PartenersComponent;
  let fixture: ComponentFixture<PartenersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PartenersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PartenersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
