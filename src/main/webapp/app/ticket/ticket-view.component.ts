import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { FormBuilder } from '@angular/forms';
import { Ticket } from 'app/model/ticket.model';

@Component({
  selector: 'jhi-ticket-view',
  templateUrl: './ticket-view.component.html'
})
export class TicketViewComponent implements OnInit {
  ticket!: Ticket;

  constructor(private route: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    window.scrollTo(0, 0);
    this.route.data.subscribe(({ ticket }) => {
      this.ticket = ticket;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
