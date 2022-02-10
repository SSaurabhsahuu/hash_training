import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TabsComponent } from './tabs/tabs.component';
import { ListComponent } from './list/list.component';

const routes = [
  { path: 'characters', component: TabsComponent, children: [
    { path: '', redirectTo: 'all', pathMatch: 'full' },
    { path: ':side', component: ListComponent }
  ] },
  // for lazy loading loadChildren is used which downloads create-character module only when it is used
  { path: 'new-character', loadChildren: './create-character/create-character.module#CreateCharacterModule' },
  { path: '**', redirectTo: '/characters' }        // for any inactive path redirect to /characters
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports:[
    RouterModule
  ]
})
export class AppRoutingModule {}      // ignore this error
