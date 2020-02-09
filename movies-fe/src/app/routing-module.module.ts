import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {RouteGuardService} from './route-guard.service';
import {HomeComponent} from './home/home.component';
import {MyselfComponent} from './myself/myself.component';
import {ExamComponent} from './exam/exam.component';
import {UsersComponent} from './users/users.component';
import {GeneralsComponent} from './generals/generals.component';
import {ExampleComponent} from './example/example.component';
import {MovieComponent} from './movie/movie.component';
import {AddMovieComponent} from './add-movie/add-movie.component';
import {ErrorComponent} from './error/error.component';
import {FavouriteMovieComponent} from './favourite-movie/favourite-movie.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: '',
    pathMatch: 'full',
    component: HomeComponent
  },
  {
    path: 'movies',
    component: MovieComponent,
    canActivate: [RouteGuardService]
  },
  {
    path: 'add-movie',
    component: AddMovieComponent,
    canActivate: [RouteGuardService]
  },
  {
    path: 'movies/favourite',
    component: FavouriteMovieComponent,
    canActivate: [RouteGuardService]
  },
  {
    path: 'error',
    component: ErrorComponent
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
