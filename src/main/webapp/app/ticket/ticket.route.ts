import { ActivatedRouteSnapshot, Resolve, Router, Routes } from '@angular/router';
import { TicketComponent } from 'app/ticket/ticket.component';
import { Injectable } from '@angular/core';
import { EMPTY, Observable, of } from 'rxjs';
import { flatMap } from 'rxjs/operators';
import { HttpResponse } from '@angular/common/http';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITicket, Ticket } from 'app/model/ticket.model';
import { TicketService } from 'app/ticket/ticket.service';
import { TicketViewComponent } from 'app/ticket/ticket-view.component';

@Injectable({ providedIn: 'root' })
export class TicketResolve implements Resolve<ITicket> {
  constructor(private service: TicketService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITicket> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service
        .query({
          field: '_id',
          keyword: id
        })
        .pipe(
          flatMap((res: HttpResponse<Ticket[]>) => {
            if (res.body) {
              return of(res.body[0]);
            } else {
              this.router.navigate(['404']);
              return EMPTY;
            }
          })
        );
    }
    return of(new Ticket());
  }
}

export const TICKET_ROUTE: Routes = [
  {
    path: '',
    component: TicketComponent,
    data: {
      pageTitle: 'Tokoin'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: TicketViewComponent,
    resolve: {
      ticket: TicketResolve
    },
    data: {
      pageTitle: 'Tokoin'
    },
    canActivate: [UserRouteAccessService]
  }
];
