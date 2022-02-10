import { Component, OnInit, OnDestroy, Input  } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { CourseService } from '../course.service';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit, OnDestroy  {
 // @Input() courses_of_list;
 courses_of_list=[];
 cart=[];
 totalAmount:number;
 loadedNumber=0;

  activatedRoute: ActivatedRoute;
  cService: CourseService;
  loadedpage = 'courses';
  subscription: { unsubscribe: () => void; };

  constructor(activatedRoute: ActivatedRoute, cService: CourseService) {
    this.activatedRoute = activatedRoute;
    this.cService = cService;


  }

  ngOnInit() {

    this.activatedRoute.params.subscribe(
      (params) => {
        console.log(" getCourses in courses");                      // get courses from service
        this.courses_of_list = this.cService.getCourses();
        this.cart = this.cService.getCartItems();
        this.totalAmount=this.cService.getTotalAmount();

      }
    );
    this.subscription = this.cService.charactersChanged.subscribe(
      () => {
        this.totalAmount = this.cService.getTotalAmount();
      }
    );

  }

  ngOnDestroy() {
    //this.subscription.unsubscribe();
  }


}
