import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CartShoppingComponent } from './cart-shopping/cart-shopping.component';
import { CartComponent } from './cart/cart.component';
import { DetailComponent } from './detail/detail.component';
import { ListComponent } from './list/list.component';
import { WishlistComponent } from './wishlist/wishlist.component';
//import { ProfileModule } from './profile/profile.module';   does not load chunk files

const routes: Routes = [
  { path: 'courses', component: ListComponent
 },
 { path: 'cart', component: CartShoppingComponent
 },
 { path: 'wishlist', component: WishlistComponent
 },
 { path: 'detail', component: DetailComponent
 },
// for lazy loading loadChildren is used which downloads create-character module only when it is used
{ path: 'profile',loadChildren: () => import("./profile/profile.module").then(m => m.ProfileModule)},
{ path: '**', redirectTo: '/courses' }        // for any inactive path redirect to /characters
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
