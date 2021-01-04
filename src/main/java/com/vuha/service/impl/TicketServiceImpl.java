package com.vuha.service.impl;

import com.vuha.domain.Organization;
import com.vuha.domain.Ticket;
import com.vuha.domain.User;
import com.vuha.repository.OrganizationRepository;
import com.vuha.repository.TicketRepository;
import com.vuha.repository.UserRepository;
import com.vuha.service.TicketService;
import com.vuha.service.mapper.TicketMapper;
import com.vuha.web.rest.vm.TicketVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TicketMapper ticketMapper;

    @Override
    public List<TicketVM> search(String field, String keyword) {
        List<Ticket> tickets = ticketRepository.search(field, keyword);
        return ticketMapper.toDto(tickets).stream().peek(item -> {
            Optional<User> assignee = userRepository.findById(item.getAssigneeId());
            assignee.ifPresent(value -> item.setAssigneeName(value.getName()));

            Optional<User> submitter = userRepository.findById(item.getSubmitterId());
            submitter.ifPresent(value -> item.setSubmitterName(value.getName()));

            Optional<Organization> organization = organizationRepository.findById(item.getOrganizationId());
            organization.ifPresent(value -> item.setOrganizationName(value.getName()));
        }).collect(Collectors.toList());
    }
}
