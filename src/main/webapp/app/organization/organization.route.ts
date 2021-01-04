import { ActivatedRouteSnapshot, Resolve, Router, Routes } from '@angular/router';

import { OrganizationComponent } from './organization.component';
import { Injectable } from '@angular/core';
import { IOrganization, Organization } from 'app/model/organization.model';
import { EMPTY, Observable, of } from 'rxjs';
import { OrganizationService } from 'app/organization/organization.service';
import { flatMap } from 'rxjs/operators';
import { HttpResponse } from '@angular/common/http';
import { OrganizationViewComponent } from 'app/organization/organization-view.component';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';

@Injectable({ providedIn: 'root' })
export class OrganizationResolve implements Resolve<IOrganization> {
  constructor(private service: OrganizationService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IOrganization> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service
        .query({
          field: '_id',
          keyword: id
        })
        .pipe(
          flatMap((res: HttpResponse<Organization[]>) => {
            if (res.body) {
              return of(res.body[0]);
            } else {
              this.router.navigate(['404']);
              return EMPTY;
            }
          })
        );
    }
    return of(new Organization());
  }
}

export const ORGANIZATION_ROUTE: Routes = [
  {
    path: '',
    component: OrganizationComponent,
    data: {
      pageTitle: 'Tokoin'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: OrganizationViewComponent,
    resolve: {
      organization: OrganizationResolve
    },
    data: {
      pageTitle: 'Tokoin'
    },
    canActivate: [UserRouteAccessService]
  }
];
