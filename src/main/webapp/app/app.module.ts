import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { TokoinSharedModule } from 'app/shared/shared.module';
import { TokoinCoreModule } from 'app/core/core.module';
import { TokoinAppRoutingModule } from './app-routing.module';
import { TokoinHomeModule } from './home/home.module';
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ErrorComponent } from './layouts/error/error.component';
import { TokoinOrganizationModule } from 'app/organization/organization.module';
import { TokoinUserModule } from 'app/user/user.module';
import { TokoinTicketModule } from 'app/ticket/ticket.module';

@NgModule({
  imports: [
    BrowserModule,
    TokoinSharedModule,
    TokoinCoreModule,
    TokoinHomeModule,
    TokoinAppRoutingModule,
    TokoinOrganizationModule,
    TokoinUserModule,
    TokoinTicketModule
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, FooterComponent],
  bootstrap: [MainComponent]
})
export class TokoinAppModule {}
