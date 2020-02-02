import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {RouteGuardService} from './route-guard.service';
import {ContactsComponent} from './contacts/contacts.component';
import {HomeComponent} from './home/home.component';
import {MyselfComponent} from './myself/myself.component';
import {ExamComponent} from './exam/exam.component';
import {UsersComponent} from './users/users.component';
import {GeneralsComponent} from './generals/generals.component';
import {ExampleComponent} from './example/example.component';
import {MovieComponent} from './movie/movie.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: '',
    pathMatch: 'full',
    component: HomeComponent
  },
  {
    path: 'contacts/:id',
    component: ContactsComponent,
    canActivate: [RouteGuardService]
  },
  {
    path: 'contacts/:id/delete/:idcontact',
    component: ContactsComponent,
    canActivate: [RouteGuardService]
  },
  {
    path: 'movies',
    component: MovieComponent,
    canActivate: [RouteGuardService]
  },
  {
    path: 'myself',
    component: MyselfComponent
  },
  {
    path: 'exam',
    component: ExamComponent
  },
  {
    path: 'users',
    component: UsersComponent
  },
  {
    path: 'generals',
    component: GeneralsComponent
  },
  {
    path: 'example',
    component: ExampleComponent
  }
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  declarations: [],
  exports: [
    RouterModule
  ],
  providers: [
    RouteGuardService
  ]
})
export class RoutingModuleModule { }
