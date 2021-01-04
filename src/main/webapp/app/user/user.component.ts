import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Account } from 'app/core/user/account.model';
import { ActivatedRoute } from '@angular/router';
import { AccountService } from 'app/core/auth/account.service';
import { HttpResponse } from '@angular/common/http';
import { Organization } from 'app/model/organization.model';
import { FormBuilder } from '@angular/forms';
import { IUser, User } from 'app/model/user.model';
import { UserService } from 'app/user/user.service';

@Component({
  selector: 'jhi-user',
  templateUrl: './user.component.html',
  styleUrls: ['user.scss']
})
export class UserComponent implements OnInit, OnDestroy {
  users?: IUser[];
  account: Account | null = null;
  authSubscription?: Subscription;
  fields: string[] = [
    '_id',
    'url',
    'external_id',
    'name',
    'alias',
    'created_at',
    'active',
    'verified',
    'shared',
    'locale',
    'timezone',
    'last_login_at',
    'email',
    'phone',
    'signature',
    'organization_id',
    'tags',
    'suspended',
    'role'
  ];

  editForm = this.fb.group({
    field: [],
    keyword: []
  });

  constructor(
    private route: ActivatedRoute,
    private accountService: AccountService,
    private userService: UserService,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.authSubscription = this.accountService.getAuthenticationState().subscribe(account => (this.account = account));
    this.route.data.subscribe(() => {
      window.scrollTo(0, 0);
    });
    this.loadDataUser();
  }

  ngOnDestroy(): void {
    if (this.authSubscription) {
      this.authSubscription.unsubscribe();
    }
  }

  trackId(index: number, item: IUser): number {
    return item._id!;
  }

  private loadDataUser(): void {
    this.userService
      .query({
        field: '',
        keyword: ''
      })
      .subscribe((res: HttpResponse<User[]>) => {
        this.users = res.body ? res.body : [];
      });
  }

  search(): void {
    this.userService
      .query({
        field: this.editForm.get(['field'])!.value != null ? this.editForm.get(['field'])!.value : '',
        keyword: this.editForm.get(['keyword'])!.value != null ? this.editForm.get(['keyword'])!.value : ''
      })
      .subscribe((res: HttpResponse<Organization[]>) => {
        this.users = res.body ? res.body : [];
      });
  }
}
