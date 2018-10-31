import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'home-layout',
  template: '<top-nav></top-nav><router-outlet></router-outlet>',
  styles: []
})
export class HomeLayoutComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
