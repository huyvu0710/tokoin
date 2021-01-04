import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Account } from 'app/core/user/account.model';
import { ActivatedRoute } from '@angular/router';
import { AccountService } from 'app/core/auth/account.service';
import { HttpResponse } from '@angular/common/http';
import { IOrganization, Organization } from 'app/model/organization.model';
import { FormBuilder } from '@angular/forms';
import { ITicket } from 'app/model/ticket.model';
import { TicketService } from 'app/ticket/ticket.service';

@Component({
  selector: 'jhi-ticket',
  templateUrl: './ticket.component.html',
  styleUrls: ['ticket.scss']
})
export class TicketComponent implements OnInit, OnDestroy {
  tickets?: ITicket[];
  account: Account | null = null;
  authSubscription?: Subscription;
  fields: string[] = [
    '_id',
    'url',
    'external_id',
    'created_at',
    'type',
    'subject',
    'description',
    'priority',
    'status',
    'submitter_id',
    'assignee_id',
    'organization_id',
    'tags',
    'has_incidents',
    'due_at',
    'via'
  ];

  editForm = this.fb.group({
    field: [],
    keyword: []
  });

  constructor(
    private route: ActivatedRoute,
    private accountService: AccountService,
    private ticketService: TicketService,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.authSubscription = this.accountService.getAuthenticationState().subscribe(account => (this.account = account));
    this.route.data.subscribe(() => {
      window.scrollTo(0, 0);
    });
    this.loadDataTicket();
  }

  ngOnDestroy(): void {
    if (this.authSubscription) {
      this.authSubscription.unsubscribe();
    }
  }

  trackId(index: number, item: IOrganization): number {
    return item._id!;
  }

  private loadDataTicket(): void {
    this.ticketService
      .query({
        field: '',
        keyword: ''
      })
      .subscribe((res: HttpResponse<Organization[]>) => {
        this.tickets = res.body ? res.body : [];
      });
  }

  search(): void {
    this.ticketService
      .query({
        field: this.editForm.get(['field'])!.value != null ? this.editForm.get(['field'])!.value : '',
        keyword: this.editForm.get(['keyword'])!.value != null ? this.editForm.get(['keyword'])!.value : ''
      })
      .subscribe((res: HttpResponse<Organization[]>) => {
        this.tickets = res.body ? res.body : [];
      });
  }
}
