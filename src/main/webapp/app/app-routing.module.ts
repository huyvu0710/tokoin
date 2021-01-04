import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { errorRoute } from './layouts/error/error.route';
import { navbarRoute } from './layouts/navbar/navbar.route';
import { DEBUG_INFO_ENABLED } from 'app/app.constants';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';

const LAYOUT_ROUTES = [navbarRoute, ...errorRoute];

@NgModule({
  imports: [
    RouterModule.forRoot(
      [
        {
          path: 'admin',
          data: {
            authorities: ['ROLE_ADMIN']
          },
          canActivate: [UserRouteAccessService],
          loadChildren: () => import('./admin/admin-routing.module').then(m => m.AdminRoutingModule)
        },
        {
          path: 'organization',
          loadChildren: () => import('./organization/organization.module').then(m => m.TokoinOrganizationModule)
        },
        {
          path: 'user',
          loadChildren: () => import('./user/user.module').then(m => m.TokoinUserModule)
        },
        {
          path: 'ticket',
          loadChildren: () => import('./ticket/ticket.module').then(m => m.TokoinTicketModule)
        },
        ...LAYOUT_ROUTES
      ],
      { enableTracing: DEBUG_INFO_ENABLED }
    )
  ],
  exports: [RouterModule]
})
export class TokoinAppRoutingModule {}
