import { Component, Input, OnInit } from '@angular/core';
import { CourseService } from '../course.service';
import { cart } from '../cart';
import { CdkDragDrop } from '@angular/cdk/drag-drop';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  @Input() courses_of_cart;
  @Input() totalAmount_cart;
           currentRoute;
  cService: CourseService;

  constructor(cService: CourseService) {
    this.cService = cService;
    this.currentRoute=this.cService.currentRoute;
  }
  ngOnInit() {

  }


  onDelete(){
    this.cService.deleteFromCart(this.courses_of_cart.name, this.courses_of_cart.cost );
  }
  onMoveToWishlist()
  {
    this.courses_of_cart.starPath=this.cService.clickStar(this.courses_of_cart.name,this.courses_of_cart.starPath)
    this.onDelete();
  }
  drop(event: CdkDragDrop<cart>){
    
  }
}
