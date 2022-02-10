import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { DragDropModule } from "@angular/cdk/drag-drop";

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CoursesComponent } from './courses/courses.component';
import { CartComponent } from './cart/cart.component';
//import { ProfileComponent } from './profile/profile.component';
import { WishlistComponent } from './wishlist/wishlist.component';
import { HeaderComponent } from './header/header.component';
import { ListComponent } from './list/list.component';
import { CourseService } from './course.service';
import { LogService } from './log.service';
import { CartShoppingComponent } from './cart-shopping/cart-shopping.component';
import { DetailComponent } from './detail/detail.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  declarations: [
    AppComponent,
    CoursesComponent,
    CartComponent,
   // ProfileComponent,
    WishlistComponent,
    HeaderComponent,
    ListComponent,
    CartShoppingComponent,
    DetailComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    DragDropModule,

  ],
  providers: [CourseService,LogService],
  bootstrap: [AppComponent]
})
export class AppModule { }
