package com.vuha.service.mapper;

import com.vuha.domain.Ticket;
import com.vuha.service.EntityMapper;
import com.vuha.web.rest.vm.TicketVM;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface TicketMapper extends EntityMapper<TicketVM, Ticket> {

}
