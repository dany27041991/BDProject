import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { NavbarComponent } from './navbar/navbar.component';
import {AngularFontAwesomeModule} from 'angular-font-awesome';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {RoutingModuleModule} from './routing-module.module';
import {AuthService} from './services/auth.service';
import { CompareValidatorDirective } from './shared/compare-validator.directive';
import { ContactsComponent } from './contacts/contacts.component';
import {ContactService} from './services/contact.service';
import { HomeComponent } from './home/home.component';
import { ModalBasicComponent } from './modal-basic/modal-basic.component';
import { ModalBasicUpdateComponent } from './modal-basic-update/modal-basic-update.component';
import { MyselfComponent } from './myself/myself.component';
import { ExamComponent } from './exam/exam.component';
import { UsersComponent } from './users/users.component';
import { GeneralsComponent } from './generals/generals.component';
import { ExampleComponent } from './example/example.component';
import { MovieComponent } from './movie/movie.component';
import {MovieService} from './services/movie.service';
import {NgxPaginationModule} from 'ngx-pagination';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {RatingModule} from 'ng-starrating';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    NavbarComponent,
    CompareValidatorDirective,
    ContactsComponent,
    HomeComponent,
    ModalBasicComponent,
    ModalBasicUpdateComponent,
    MyselfComponent,
    ExamComponent,
    UsersComponent,
    GeneralsComponent,
    ExampleComponent,
    MovieComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    NgxPaginationModule,
    NgbModule,
    AngularFontAwesomeModule,
    HttpClientModule,
    FormsModule,
    RoutingModuleModule,
    RatingModule
  ],
  providers: [AuthService, ContactService, MovieService],
  bootstrap: [AppComponent]
})
export class AppModule { }
