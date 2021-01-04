export interface ITicket {
  _id?: number;
  url?: string;
  external_id?: string;
  created_at?: string;
  type?: string;
  subject?: string;
  description?: string;
  priority?: string;
  status?: string;
  submitter_id?: number;
  assignee_id?: number;
  organization_id?: number;
  tags?: string[];
  has_incidents?: any;
  due_at?: string;
  via?: string;
  organization_name?: string;
  assignee_name?: string;
  submitter_name?: string;
}

export class Ticket implements ITicket {
  constructor(
    public _id?: number,
    public url?: string,
    public external_id?: string,
    public created_at?: string,
    public type?: string,
    public subject?: string,
    public description?: string,
    public priority?: string,
    public status?: string,
    public submitter_id?: number,
    public assignee_id?: number,
    public organization_id?: number,
    public tags?: string[],
    public has_incidents?: any,
    public due_at?: string,
    public via?: string,
    public organization_name?: string,
    public assignee_name?: string,
    public submitter_name?: string
  ) {}
}
