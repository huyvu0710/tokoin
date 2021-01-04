package com.vuha.service.impl;

import com.vuha.domain.Organization;
import com.vuha.domain.Ticket;
import com.vuha.domain.User;
import com.vuha.repository.OrganizationRepository;
import com.vuha.repository.TicketRepository;
import com.vuha.repository.UserRepository;
import com.vuha.service.UserService;
import com.vuha.service.mapper.UserMapper;
import com.vuha.web.rest.vm.UserVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Override
    public List<UserVM> search(String field, String keyword) {
        List<User> users = userRepository.search(field, keyword);
        return userMapper.toDto(users).stream().peek(item -> {
            List<Ticket> assigneeTicketSubjects = ticketRepository.search("assignee_id", String.valueOf(item.getId()));
            item.setAssigneeTicketSubjects(assigneeTicketSubjects.stream().map(Ticket::getSubject).collect(Collectors.toList()));

            List<Ticket> submittedTicketSubjects = ticketRepository.search("submitter_id", String.valueOf(item.getId()));
            item.setSubmittedTicketSubjects(submittedTicketSubjects.stream().map(Ticket::getSubject).collect(Collectors.toList()));

            Optional<Organization> organization = organizationRepository.findById(item.getOrganizationId());
            organization.ifPresent(value -> item.setOrganizationName(value.getName()));
        }).collect(Collectors.toList());
    }
}
