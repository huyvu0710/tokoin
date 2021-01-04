import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TokoinSharedModule } from 'app/shared/shared.module';
import { ORGANIZATION_ROUTE } from './organization.route';
import { OrganizationComponent } from 'app/organization/organization.component';
import { OrganizationViewComponent } from 'app/organization/organization-view.component';

@NgModule({
  imports: [TokoinSharedModule, RouterModule.forChild(ORGANIZATION_ROUTE)],
  declarations: [OrganizationComponent, OrganizationViewComponent]
})
export class TokoinOrganizationModule {}
