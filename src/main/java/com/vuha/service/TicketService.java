package com.vuha.service;

import com.vuha.web.rest.vm.TicketVM;

import java.util.List;

public interface TicketService {

    List<TicketVM> search(String field, String keyword);

}
