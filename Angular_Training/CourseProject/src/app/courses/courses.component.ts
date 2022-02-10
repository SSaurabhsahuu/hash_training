import { Component, Input, OnInit } from '@angular/core';
import { CourseService } from '../course.service';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.css']
})
export class CoursesComponent implements OnInit {

  @Input() courses_of_course;
  currentRoute: string;
  cService: CourseService;

  constructor(cService: CourseService) {
    this.cService = cService;
    this.currentRoute=this.cService.currentRoute;
  }

  ngOnInit() {

  }

  onAddToCart() {
    // this.character.side = side;
    // this.sideAssigned.emit({name: this.character.name, side: side});
    this.cService.addToCart(this.courses_of_course.name, this.courses_of_course.instructor, this.courses_of_course.starPath, this.courses_of_course.cost );

  }
  onClickStar()
  {
    this.courses_of_course.starPath=this.cService.clickStar(this.courses_of_course.name,this.courses_of_course.starPath)
  }
  onClickDetail(){
    this.cService.putDetail(this.courses_of_course.name);
  }
}
