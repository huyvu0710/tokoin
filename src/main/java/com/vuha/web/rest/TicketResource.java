package com.vuha.web.rest;

import com.vuha.service.TicketService;
import com.vuha.web.rest.vm.TicketVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TicketResource {

    private final TicketService ticketService;

    public TicketResource(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/public/tickets")
    ResponseEntity<List<TicketVM>> searchTicket(@RequestParam(required = false) String field,
                                                      @RequestParam(required = false) String keyword) {
        List<TicketVM> tickets = ticketService.search(field, keyword);
        return ResponseEntity.ok(tickets);
    }

}
