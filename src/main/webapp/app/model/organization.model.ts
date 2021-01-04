export interface IOrganization {
  _id?: number;
  url?: string;
  external_id?: string;
  name?: string;
  domain_names?: string[];
  created_at?: string;
  details?: string;
  shared_tickets?: any;
  tags?: string[];
  ticket_subjects?: string[];
  user_names?: string[];
}

export class Organization implements IOrganization {
  constructor(
    public _id?: number,
    public url?: string,
    public external_id?: string,
    public name?: string,
    public domain_names?: string[],
    public created_at?: string,
    public details?: string,
    public shared_tickets?: any,
    public tags?: string[],
    public ticket_subjects?: string[],
    public user_names?: string[]
  ) {}
}
