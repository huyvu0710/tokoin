package com.vuha.service.mapper;

import com.vuha.domain.Organization;
import com.vuha.service.EntityMapper;
import com.vuha.web.rest.vm.OrganizationVM;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface OrganizationMapper extends EntityMapper<OrganizationVM, Organization> {

}
