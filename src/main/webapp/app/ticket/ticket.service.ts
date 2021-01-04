import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITicket } from 'app/model/ticket.model';

type EntityResponseType = HttpResponse<ITicket>;
type EntityArrayResponseType = HttpResponse<ITicket[]>;

@Injectable({ providedIn: 'root' })
export class TicketService {
  public resourceUrl = SERVER_API_URL + 'api/public/tickets';

  constructor(protected http: HttpClient) {}

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITicket[]>(this.resourceUrl, { params: options, observe: 'response' });
  }
}
