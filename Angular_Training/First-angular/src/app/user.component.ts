import { Component, Input, Output } from '@angular/core';
import { EventEmitter } from '@angular/core';

@Component({
  selector: 'app-user',
  template: `
  <input type="text" (input)="onUserInput($event)" [value]="name" >
 <!-- <input type="text" [(ngModel)]="name"> -->
  <p>Hello {{name}}</p>
  <p>Custom Component</p>
  `

})
export class UserComponent {
  @Input() name;                               // custom properties to pass data from app.components.html
  @Output() nameChanged= new EventEmitter();    // custom event to

  onUserInput(event){
    //  this.name=event.target.value;
    this.nameChanged.emit(event.target.value);
  }
}
