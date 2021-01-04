export interface IUser {
  _id?: number;
  url?: string;
  external_id?: string;
  name?: string;
  alias?: string;
  created_at?: string;
  active?: any;
  verified?: any;
  shared?: any;
  locale?: string;
  timezone?: string;
  last_login_at?: string;
  email?: string;
  phone?: string;
  signature?: string;
  organization_id?: number;
  tags?: string[];
  suspended?: any;
  role?: string;
  assignee_ticket_subjects?: string[];
  submitted_ticket_subjects?: string[];
  organization_name?: string[];
}

export class User implements IUser {
  constructor(
    public _id?: number,
    public url?: string,
    public external_id?: string,
    public name?: string,
    public alias?: string,
    public created_at?: string,
    public active?: any,
    public verified?: any,
    public shared?: any,
    public locale?: string,
    public timezone?: string,
    public last_login_at?: string,
    public email?: string,
    public phone?: string,
    public signature?: string,
    public organization_id?: number,
    public tags?: string[],
    public suspended?: any,
    public role?: string,
    public assignee_ticket_subjects?: string[],
    public submitted_ticket_subjects?: string[],
    public organization_name?: string[]
  ) {}
}
