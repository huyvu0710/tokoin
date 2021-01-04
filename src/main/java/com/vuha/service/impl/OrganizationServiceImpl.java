package com.vuha.service.impl;

import com.vuha.domain.Organization;
import com.vuha.domain.Ticket;
import com.vuha.domain.User;
import com.vuha.repository.OrganizationRepository;
import com.vuha.repository.TicketRepository;
import com.vuha.repository.UserRepository;
import com.vuha.service.OrganizationService;
import com.vuha.service.mapper.OrganizationMapper;
import com.vuha.web.rest.vm.OrganizationVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrganizationMapper organizationMapper;

    @Override
    public List<OrganizationVM> search(String field, String keyword) {
        List<Organization> organizations = organizationRepository.search(field, keyword);
        return organizationMapper.toDto(organizations).stream().peek(item -> {
            List<Ticket> tickets = ticketRepository.search("organization_id", String.valueOf(item.getId()));
            item.setTicketSubjects(tickets.stream().map(Ticket::getSubject).collect(Collectors.toList()));

            List<User> users = userRepository.search("organization_id", String.valueOf(item.getId()));
            item.setUserNames(users.stream().map(User::getName).collect(Collectors.toList()));
        }).collect(Collectors.toList());
    }
}
