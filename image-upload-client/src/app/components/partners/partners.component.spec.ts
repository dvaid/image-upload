import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { partnersComponent } from './partners.component';

describe('partnersComponent', () => {
  let component: partnersComponent;
  let fixture: ComponentFixture<partnersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ partnersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(partnersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
