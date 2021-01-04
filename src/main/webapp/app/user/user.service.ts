import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IUser } from 'app/model/user.model';

type EntityResponseType = HttpResponse<IUser>;
type EntityArrayResponseType = HttpResponse<IUser[]>;

@Injectable({ providedIn: 'root' })
export class UserService {
  public resourceUrl = SERVER_API_URL + 'api/public/users';

  constructor(protected http: HttpClient) {}

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IUser[]>(this.resourceUrl, { params: options, observe: 'response' });
  }
}
