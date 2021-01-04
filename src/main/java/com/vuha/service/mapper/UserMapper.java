package com.vuha.service.mapper;

import com.vuha.domain.User;
import com.vuha.service.EntityMapper;
import com.vuha.web.rest.vm.UserVM;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface UserMapper extends EntityMapper<UserVM, User> {

}
