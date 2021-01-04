import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { FormBuilder } from '@angular/forms';
import { Organization } from 'app/model/organization.model';

@Component({
  selector: 'jhi-organization-view',
  templateUrl: './organization-view.component.html'
})
export class OrganizationViewComponent implements OnInit {
  organization!: Organization;

  constructor(private route: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    window.scrollTo(0, 0);
    this.route.data.subscribe(({ organization }) => {
      this.organization = organization;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
