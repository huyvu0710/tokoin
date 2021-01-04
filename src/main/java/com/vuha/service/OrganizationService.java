package com.vuha.service;

import com.vuha.web.rest.vm.OrganizationVM;

import java.util.List;

public interface OrganizationService {

    List<OrganizationVM> search(String field, String keyword);

}
