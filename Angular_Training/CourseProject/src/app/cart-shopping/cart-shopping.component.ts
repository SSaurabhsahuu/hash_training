import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CourseService } from '../course.service';

@Component({
  selector: 'app-cart-shopping',
  templateUrl: './cart-shopping.component.html',
  styleUrls: ['./cart-shopping.component.css']
})
export class CartShoppingComponent implements OnInit {

  cart=[];
  totalAmount:number=0;
  activatedRoute: ActivatedRoute;
  cService: CourseService;
  subscription: { unsubscribe: () => void; };

  constructor(activatedRoute: ActivatedRoute, cService: CourseService,private router: Router) {
    this.activatedRoute = activatedRoute;
    this.cService = cService;

  }

  ngOnInit() {


    this.activatedRoute.params.subscribe(
      (params) => {
        console.log(" getCourses in cart");                      // get courses from service
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

  onCheckout(){
    alert("Courses succesfully Purchased !! ");
    this.cService.deleteAllCart();
    this.router.navigate([`/courses`]);
  }
}
