import { Component } from '@angular/core';
import { CourseService } from './course.service';
import { CdkDragDrop,transferArrayItem } from '@angular/cdk/drag-drop';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'CourseProject';
  courses_of_app = [];
  cService: CourseService;

  ownedAnimals=[
    "Dog",
    "Cat",
    "Fish",
    "snake"
  ];
  animalWishlist=[
    "Tiger",
    "Lion",
    "crocodile",
    "parrot"
  ];
  constructor( cService: CourseService) {

    this.cService = cService;
  }
  onDrop(event:CdkDragDrop<string []> ){

  }
  getCourses() {
    console.log(" getCourses in courses");
    this.courses_of_app = this.cService.getCourses();
    return this.courses_of_app;
  }
}
