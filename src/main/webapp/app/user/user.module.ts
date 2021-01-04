import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TokoinSharedModule } from 'app/shared/shared.module';
import { USER_ROUTE } from 'app/user/user.route';
import { UserComponent } from 'app/user/user.component';
import { UserViewComponent } from 'app/user/user-view.component';

@NgModule({
  imports: [TokoinSharedModule, RouterModule.forChild(USER_ROUTE)],
  declarations: [UserComponent, UserViewComponent]
})
export class TokoinUserModule {}
