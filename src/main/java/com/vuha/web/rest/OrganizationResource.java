package com.vuha.web.rest;

import com.vuha.service.OrganizationService;
import com.vuha.web.rest.vm.OrganizationVM;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrganizationResource {

    private final OrganizationService organizationService;

    public OrganizationResource(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping("/public/organizations")
    ResponseEntity<List<OrganizationVM>> searchOrganization(@RequestParam(required = false) String field,
                                                            @RequestParam(required = false) String keyword) {
        List<OrganizationVM> organizations = organizationService.search(field, keyword);
        return ResponseEntity.ok(organizations);
    }

}
