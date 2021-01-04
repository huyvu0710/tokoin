import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Account } from 'app/core/user/account.model';
import { ActivatedRoute } from '@angular/router';
import { AccountService } from 'app/core/auth/account.service';
import { OrganizationService } from 'app/organization/organization.service';
import { HttpResponse } from '@angular/common/http';
import { IOrganization, Organization } from 'app/model/organization.model';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'jhi-organization',
  templateUrl: './organization.component.html',
  styleUrls: ['organization.scss']
})
export class OrganizationComponent implements OnInit, OnDestroy {
  organization!: Organization;
  organizations?: IOrganization[];
  account: Account | null = null;
  authSubscription?: Subscription;
  fields: string[] = ['_id', 'url', 'external_id', 'name', 'domain_names', 'created_at', 'details', 'shared_tickets', 'tags'];

  editForm = this.fb.group({
    field: [],
    keyword: []
  });

  constructor(
    private route: ActivatedRoute,
    private accountService: AccountService,
    private organizationService: OrganizationService,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.authSubscription = this.accountService.getAuthenticationState().subscribe(account => (this.account = account));
    this.route.data.subscribe(() => {
      window.scrollTo(0, 0);
    });
    this.loadDataOrganization();
  }

  ngOnDestroy(): void {
    if (this.authSubscription) {
      this.authSubscription.unsubscribe();
    }
  }

  trackId(index: number, item: IOrganization): number {
    return item._id!;
  }

  private loadDataOrganization(): void {
    this.organizationService
      .query({
        field: '',
        keyword: ''
      })
      .subscribe((res: HttpResponse<Organization[]>) => {
        this.organizations = res.body ? res.body : [];
      });
  }

  search(): void {
    this.organizationService
      .query({
        field: this.editForm.get(['field'])!.value != null ? this.editForm.get(['field'])!.value : '',
        keyword: this.editForm.get(['keyword'])!.value != null ? this.editForm.get(['keyword'])!.value : ''
      })
      .subscribe((res: HttpResponse<Organization[]>) => {
        this.organizations = res.body ? res.body : [];
      });
  }
}
