import { Component, Input, OnInit } from '@angular/core';
import { CourseService } from '../course.service';
import { DomSanitizer, SafeResourceUrl, } from '@angular/platform-browser';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})

export class DetailComponent implements OnInit {


    course_detail;
  currentRoute: string;
  cService: CourseService;


  mapURL: SafeResourceUrl



  constructor(cService: CourseService,public sanitizer: DomSanitizer) {
    this.cService = cService;
    this.currentRoute=this.cService.currentRoute;

  }

  ngOnInit() {
    this.course_detail=this.cService.getDetail();
    this.mapURL = this.sanitizer.bypassSecurityTrustResourceUrl(this.course_detail.url);
    console.log(" course name from detail "+this.course_detail.url);
  }

  onAddToCart() {
    // this.character.side = side;
    // this.sideAssigned.emit({name: this.character.name, side: side});
    this.cService.addToCart(this.course_detail.name, this.course_detail.instructor, this.course_detail.starPath, this.course_detail.cost );

  }
  onAddToWishlist()
  {
    this.course_detail.starPath=this.cService.clickStar(this.course_detail.name,this.course_detail.starPath)
  }


}
