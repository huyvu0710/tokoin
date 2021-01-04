package com.vuha.repository;

import com.vuha.domain.Ticket;
import com.vuha.util.JSONUtil;
import com.vuha.util.STRINGUtil;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TicketRepository {

    String dataDirectory = "data/tickets.json";
    Map<String, Ticket> ticketData = new HashMap<>();

    @PostConstruct
    public void init() throws IOException, ParseException {
        URL resource = getClass().getClassLoader().getResource(dataDirectory);
        if (resource != null) {
            JSONArray jsonArray = (JSONArray) JSONUtil.parseReaderFromPath(resource.getPath());
            List<Ticket> ticketList = JSONUtil.jsonArrayToObjList(jsonArray, Ticket.class);
            for (Ticket item : ticketList) {
                ticketData.put(item.getId(), item);
            }
        }
    }

    public List<Ticket> findAll() {
        return new ArrayList<>(ticketData.values());
    }

    public List<Ticket> search(String field, String keyword) {
        List<Ticket> tickets = new ArrayList<>();

        if (StringUtils.isEmpty(field)) {
            tickets = StringUtils.isEmpty(keyword) ? findAll() : new ArrayList<>();
            return tickets;
        }
        keyword = keyword.trim().toLowerCase();

        try {
            switch (field) {
                case "_id":
                    if (ticketData.containsKey(keyword)) {
                        tickets.add(ticketData.get(keyword));
                    }
                    break;
                case "url":
                    for (Map.Entry<String, Ticket> entry : ticketData.entrySet()) {
                        Ticket ticket = entry.getValue();
                        if (StringUtils.equals(ticket.getUrl(), keyword)) {
                            tickets.add(ticket);
                        }
                    }
                    break;
                case "external_id":
                    for (Map.Entry<String, Ticket> entry : ticketData.entrySet()) {
                        Ticket ticket = entry.getValue();
                        if (StringUtils.equals(ticket.getExternalId(), keyword)) {
                            tickets.add(ticket);
                        }
                    }
                    break;
                case "created_at":
                    for (Map.Entry<String, Ticket> entry : ticketData.entrySet()) {
                        Ticket ticket = entry.getValue();
                        if (ticket.getCreatedAt().equals(keyword)) {
                            tickets.add(ticket);
                        }
                    }
                    break;
                case "type":
                    for (Map.Entry<String, Ticket> entry : ticketData.entrySet()) {
                        Ticket ticket = entry.getValue();
                        if (StringUtils.equals(ticket.getType(), keyword)) {
                            tickets.add(ticket);
                        }
                    }
                    break;
                case "subject":
                    for (Map.Entry<String, Ticket> entry : ticketData.entrySet()) {
                        Ticket ticket = entry.getValue();
                        if (STRINGUtil.separateWord(ticket.getSubject().toLowerCase()).contains(keyword)) {
                            tickets.add(ticket);
                        }
                    }
                    break;
                case "description":
                    for (Map.Entry<String, Ticket> entry : ticketData.entrySet()) {
                        Ticket ticket = entry.getValue();
                        if (STRINGUtil.separateWord(ticket.getDescription().toLowerCase()).contains(keyword)) {
                            tickets.add(ticket);
                        }
                    }
                    break;
                case "priority":
                    for (Map.Entry<String, Ticket> entry : ticketData.entrySet()) {
                        Ticket ticket = entry.getValue();
                        if (StringUtils.equals(ticket.getPriority(), keyword)) {
                            tickets.add(ticket);
                        }
                    }
                    break;
                case "status":
                    for (Map.Entry<String, Ticket> entry : ticketData.entrySet()) {
                        Ticket ticket = entry.getValue();
                        if (StringUtils.equals(ticket.getStatus().toLowerCase(), keyword)) {
                            tickets.add(ticket);
                        }
                    }
                    break;
                case "submitter_id":
                    for (Map.Entry<String, Ticket> entry : ticketData.entrySet()) {
                        Ticket ticket = entry.getValue();
                        if (ticket.getSubmitterId() == Integer.parseInt(keyword)) {
                            tickets.add(ticket);
                        }
                    }
                    break;
                case "assignee_id":
                    for (Map.Entry<String, Ticket> entry : ticketData.entrySet()) {
                        Ticket ticket = entry.getValue();
                        if (ticket.getAssigneeId() == Integer.parseInt(keyword)) {
                            tickets.add(ticket);
                        }
                    }
                    break;
                case "organization_id":
                    for (Map.Entry<String, Ticket> entry : ticketData.entrySet()) {
                        Ticket ticket = entry.getValue();
                        if (ticket.getOrganizationId() == Integer.parseInt(keyword)) {
                            tickets.add(ticket);
                        }
                    }
                    break;
                case "tags":
                    for (Map.Entry<String, Ticket> entry : ticketData.entrySet()) {
                        Ticket ticket = entry.getValue();
                        if (ticket.getTags().contains(keyword)) {
                            tickets.add(ticket);
                        }
                    }
                    break;
                case "has_incidents":
                    for (Map.Entry<String, Ticket> entry : ticketData.entrySet()) {
                        Ticket ticket = entry.getValue();
                        if (ticket.isHasIncidents() == Boolean.parseBoolean(keyword)) {
                            tickets.add(ticket);
                        }
                    }
                    break;
                case "due_at":
                    for (Map.Entry<String, Ticket> entry : ticketData.entrySet()) {
                        Ticket ticket = entry.getValue();
                        if (StringUtils.equals(ticket.getDue_at().toLowerCase(), keyword)) {
                            tickets.add(ticket);
                        }
                    }
                    break;
                case "via":
                    for (Map.Entry<String, Ticket> entry : ticketData.entrySet()) {
                        Ticket ticket = entry.getValue();
                        if (StringUtils.equals(ticket.getVia().toLowerCase(), keyword.toLowerCase())) {
                            tickets.add(ticket);
                        }
                    }
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tickets;
    }
}
