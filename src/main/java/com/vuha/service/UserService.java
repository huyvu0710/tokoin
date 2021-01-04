package com.vuha.service;

import com.vuha.web.rest.vm.UserVM;

import java.util.List;

public interface UserService {

    List<UserVM> search(String field, String keyword);

}
