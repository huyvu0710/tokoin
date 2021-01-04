import { ActivatedRouteSnapshot, Resolve, Router, Routes } from '@angular/router';
import { UserComponent } from 'app/user/user.component';
import { Injectable } from '@angular/core';
import { EMPTY, Observable, of } from 'rxjs';
import { flatMap } from 'rxjs/operators';
import { HttpResponse } from '@angular/common/http';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IUser, User } from 'app/model/user.model';
import { UserService } from 'app/user/user.service';
import { UserViewComponent } from 'app/user/user-view.component';

@Injectable({ providedIn: 'root' })
export class UserResolve implements Resolve<IUser> {
  constructor(private service: UserService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IUser> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service
        .query({
          field: '_id',
          keyword: id
        })
        .pipe(
          flatMap((res: HttpResponse<User[]>) => {
            if (res.body) {
              return of(res.body[0]);
            } else {
              this.router.navigate(['404']);
              return EMPTY;
            }
          })
        );
    }
    return of(new User());
  }
}

export const USER_ROUTE: Routes = [
  {
    path: '',
    component: UserComponent,
    data: {
      pageTitle: 'Tokoin'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: UserViewComponent,
    resolve: {
      user: UserResolve
    },
    data: {
      pageTitle: 'Tokoin'
    },
    canActivate: [UserRouteAccessService]
  }
];
