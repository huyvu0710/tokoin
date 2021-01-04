import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { FormBuilder } from '@angular/forms';
import { User } from 'app/model/user.model';

@Component({
  selector: 'jhi-user-view',
  templateUrl: './user-view.component.html'
})
export class UserViewComponent implements OnInit {
  user!: User;

  constructor(private route: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    window.scrollTo(0, 0);
    this.route.data.subscribe(({ user }) => {
      this.user = user;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
