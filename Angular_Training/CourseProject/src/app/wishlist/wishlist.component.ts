import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CourseService } from '../course.service';

@Component({
  selector: 'app-wishlist',
  templateUrl: './wishlist.component.html',
  styleUrls: ['./wishlist.component.css']
})
export class WishlistComponent implements OnInit {
  courses_of_wishlist=[];
  cart=[];
  totalAmount:number;
  currentRoute: string;
  loadedNumber=0;

   activatedRoute: ActivatedRoute;
   cService: CourseService;

   subscription: { unsubscribe: () => void; };


   constructor(activatedRoute: ActivatedRoute, cService: CourseService) {
     this.activatedRoute = activatedRoute;
     this.cService = cService;
     this.currentRoute=this.cService.currentRoute;

   }

   ngOnInit() {

     this.activatedRoute.params.subscribe(
       (params) => {
         console.log(" get Wishlist Courses ");                      // get courses from service
         this.courses_of_wishlist = this.cService.getCourses();
         this.cart = this.cService.getCartItems();
         this.totalAmount=this.cService.getTotalAmount();

       }
     );
     this.subscription = this.cService.charactersChanged.subscribe(
       () => {
         this.totalAmount = this.cService.getTotalAmount();
         this.courses_of_wishlist = this.cService.getCourses();
         this.cart = this.cService.getCartItems();
       }
     );

   }


  filterMarkedCourses(Path){
    console.log("filter wichlist courses");
    console.log(this.courses_of_wishlist.filter(x => x.starPath == Path));
    return this.courses_of_wishlist.filter(x => x.starPath == Path);
 }
}
