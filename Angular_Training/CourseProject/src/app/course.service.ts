import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { filter } from 'rxjs/operators';

//import 'rxjs/add/operator/map';


import { LogService } from './log.service';
import { NavigationEnd, Router } from '@angular/router';

@Injectable()
export class CourseService {                           // to store all data and easier than 2 way binding
  private courses = [
    { name: 'Angular Beginners', instructor: 'Maxmil', starPath:"assets/images/white-star.png", cost: 500 ,url:"https://www.youtube.com/embed/Ata9cSC2WpM"},
    { name: 'Angular Advanced', instructor: 'Rogers', starPath:"assets/images/white-star.png", cost: 600 ,url:"https://www.youtube.com/embed/Ati-oip_HcU"},
    { name: 'React Complete Course', instructor: 'Mohan', starPath:"assets/images/white-star.png", cost: 510,url:"https://www.youtube.com/embed/4UZrsTqkcW4" },
    { name: 'JavaScript', instructor: 'Sohan', starPath:"assets/images/white-star.png", cost: 520 ,url:"https://www.youtube.com/embed/jS4aFq5-91M"},
    { name: 'HTML & CSS', instructor: 'Jonas', starPath:"assets/images/white-star.png", cost: 500 ,url:"https://www.youtube.com/embed/mU6anWqZJcc"},
    { name: 'Selenium', instructor: 'Sam', starPath:"assets/images/white-star.png", cost: 500 ,url:"https://www.youtube.com/embed/FRn5J31eAMw"}
  ];
  private detail_index:number;
  private cart=[];
  private totalAmount:number=0;
  private user= {displayname:'',firstname:'',lastname:'',about:'',
                  Designer:false,Developer:false,ProductManager:false,Sales:false,
                   Occupation:'',Experience:'', Expertise:'',note:''
                  };

  private logService: LogService;
  charactersChanged = new Subject<void>();
  currentRoute: string;

  constructor(logService: LogService,private router: Router) {
    this.logService = logService;


    router.events.pipe(filter(event => event instanceof NavigationEnd))
    .subscribe(event =>
     { if(event instanceof NavigationEnd )
         { this.currentRoute = event.url;
            console.log(event.url);
         }
     });

  }


  getCourses() {
      this.logService.writeLog(" getCourses in services");
      return this.courses;
  }
  getCartItems() {
    this.logService.writeLog(" getcart in services");
    return this.cart;
}
  addToCart(name, instructor,starPath,cost) {
    const pos = this.cart.findIndex((char) => {
      return char.name === name;
    })
    if (pos !== -1) {
      alert(`Course already exist in cart`);
      return;
    }
    starPath="assets/images/white-star.png";                  // remove wishlist from cart
    let newCart = {name: name, instructor: instructor,starPath:starPath ,cost: cost};
    this.totalAmount += cost;

    this.cart.push(newCart);

    this.clickStar(name,"assets/images/yellow-star.png")      // remove wishlist from course
    this.charactersChanged.next();
    alert(`Course added to cart`);
  }


  getTotalAmount(){
    console.log("Total Amount "+this.totalAmount);

    return this.totalAmount;
  }


 deleteFromCart(name,cost){
  const pos = this.cart.findIndex((char) => {
    return char.name === name;
  })

  this.totalAmount -= cost;
  this.cart.splice(pos,1);
  this.charactersChanged.next();
 }
 deleteAllCart(){
   this.cart.length=0;
   this.totalAmount=0;

 }
clickStar(name,starPath){
  const pos = this.courses.findIndex((char) => {
    return char.name === name;
  })

 if(starPath==="assets/images/white-star.png")
   this.courses[pos].starPath="assets/images/yellow-star.jpg";
 else
   this.courses[pos].starPath="assets/images/white-star.png";

 return this.courses[pos].starPath;
}

getProfileDetails(){
  console.log("user details");
  return this.user;
}
 addProfileDetails(detail){
  console.log("display name "+detail.displayname);
  this.user=detail;
 console.log("profile "+this.user.displayname);
 }
 getDetail(){
  console.log(" get detail index "+this.detail_index);
   return this.courses[this.detail_index];
 }
 putDetail(name){
  const pos = this.courses.findIndex((char) => {
    return char.name === name;
  })
  console.log("put detail  course index"+pos);
  this.detail_index = pos;
 }
}
