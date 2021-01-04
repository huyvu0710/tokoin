package com.vuha.web.rest.vm;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TicketVM {

    @JsonProperty("_id")
    private String id;

    @JsonProperty("url")
    private String url;

    @JsonProperty("external_id")
    private String externalId;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("type")
    private String type;

    @JsonProperty("subject")
    private String subject;

    @JsonProperty("description")
    private String description;

    @JsonProperty("priority")
    private String priority;

    @JsonProperty("status")
    private String status;

    @JsonProperty("submitter_id")
    private int submitterId;

    @JsonProperty("assignee_id")
    private int assigneeId;

    @JsonProperty("organization_id")
    private int organizationId;

    @JsonProperty("tags")
    private List<String> tags;

    @JsonProperty("has_incidents")
    private boolean hasIncidents;

    @JsonProperty("due_at")
    private String due_at;

    @JsonProperty("via")
    private String via;

    @JsonProperty("organization_name")
    private String organizationName;

    @JsonProperty("assignee_name")
    private String assigneeName;

    @JsonProperty("submitter_name")
    private String submitterName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSubmitterId() {
        return submitterId;
    }

    public void setSubmitterId(int submitterId) {
        this.submitterId = submitterId;
    }

    public int getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(int assigneeId) {
        this.assigneeId = assigneeId;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public boolean isHasIncidents() {
        return hasIncidents;
    }

    public void setHasIncidents(boolean hasIncidents) {
        this.hasIncidents = hasIncidents;
    }

    public String getDue_at() {
        return due_at;
    }

    public void setDue_at(String due_at) {
        this.due_at = due_at;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getAssigneeName() {
        return assigneeName;
    }

    public void setAssigneeName(String assigneeName) {
        this.assigneeName = assigneeName;
    }

    public String getSubmitterName() {
        return submitterName;
    }

    public void setSubmitterName(String submitterName) {
        this.submitterName = submitterName;
    }
}
