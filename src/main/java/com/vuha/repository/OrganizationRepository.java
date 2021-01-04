package com.vuha.repository;

import com.vuha.domain.Organization;
import com.vuha.util.JSONUtil;
import com.vuha.util.STRINGUtil;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;
import java.util.*;

@Service
public class OrganizationRepository {

    String dataDirectory = "data/organizations.json";
    Map<Integer, Organization> organizationData = new HashMap<>();

    @PostConstruct
    public void init() throws IOException, ParseException {
        URL resource = getClass().getClassLoader().getResource(dataDirectory);
        if (resource != null) {
            JSONArray jsonArray = (JSONArray) JSONUtil.parseReaderFromPath(resource.getPath());
            List<Organization> organizationList = JSONUtil.jsonArrayToObjList(jsonArray, Organization.class);
            for (Organization item : organizationList) {
                organizationData.put(item.getId(), item);
            }
        }
    }

    public List<Organization> findAll() {
        return new ArrayList<>(organizationData.values());
    }

    public Optional<Organization> findById(int id) {
        Organization o = organizationData.get(id);
        if (o == null) {
            return Optional.empty();
        }
        return Optional.of(o);
    }

    public List<Organization> search(String field, String keyword) {
        List<Organization> organizations = new ArrayList<>();

        if (StringUtils.isEmpty(field)) {
            organizations = StringUtils.isEmpty(keyword) ? findAll() : new ArrayList<>();
            return organizations;
        }

        try {
            switch (field) {
                case "_id":
                    int id = Integer.parseInt(keyword);
                    if (organizationData.containsKey(id)) {
                        organizations.add(organizationData.get(id));
                    }
                    break;
                case "url":
                    for (Map.Entry<Integer, Organization> entry : organizationData.entrySet()) {
                        Organization organization = entry.getValue();
                        if (StringUtils.equals(organization.getUrl(), keyword)) {
                            organizations.add(organization);
                        }
                    }
                    break;
                case "external_id":
                    for (Map.Entry<Integer, Organization> entry : organizationData.entrySet()) {
                        Organization organization = entry.getValue();
                        if (StringUtils.equals(organization.getExternalId(), keyword)) {
                            organizations.add(organization);
                        }
                    }
                    break;
                case "name":
                    for (Map.Entry<Integer, Organization> entry : organizationData.entrySet()) {
                        Organization organization = entry.getValue();
                        if (STRINGUtil.separateWord(organization.getName()).contains(keyword)) {
                            organizations.add(organization);
                        }
                    }
                    break;
                case "domain_names":
                    for (Map.Entry<Integer, Organization> entry : organizationData.entrySet()) {
                        Organization organization = entry.getValue();
                        if (organization.getDomainNames().contains(keyword)) {
                            organizations.add(organization);
                        }
                    }
                    break;
                case "created_at":
                    for (Map.Entry<Integer, Organization> entry : organizationData.entrySet()) {
                        Organization organization = entry.getValue();
                        if (StringUtils.equals(organization.getCreatedAt(), keyword)) {
                            organizations.add(organization);
                        }
                    }
                    break;
                case "details":
                    for (Map.Entry<Integer, Organization> entry : organizationData.entrySet()) {
                        Organization organization = entry.getValue();
                        if (STRINGUtil.separateWord(organization.getDetails()).contains(keyword)) {
                            organizations.add(organization);
                        }
                    }
                    break;
                case "shared_tickets":
                    for (Map.Entry<Integer, Organization> entry : organizationData.entrySet()) {
                        Organization organization = entry.getValue();
                        if (organization.isSharedTickets() == Boolean.parseBoolean(keyword)) {
                            organizations.add(organization);
                        }
                    }
                    break;
                case "tags":
                    for (Map.Entry<Integer, Organization> entry : organizationData.entrySet()) {
                        Organization organization = entry.getValue();
                        if (organization.getTags().contains(keyword)) {
                            organizations.add(organization);
                        }
                    }
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return organizations;
    }

}
