import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TokoinSharedModule } from 'app/shared/shared.module';
import { TICKET_ROUTE } from 'app/ticket/ticket.route';
import { TicketComponent } from 'app/ticket/ticket.component';
import { TicketViewComponent } from 'app/ticket/ticket-view.component';

@NgModule({
  imports: [TokoinSharedModule, RouterModule.forChild(TICKET_ROUTE)],
  declarations: [TicketComponent, TicketViewComponent]
})
export class TokoinTicketModule {}
