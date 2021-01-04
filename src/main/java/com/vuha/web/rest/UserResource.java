package com.vuha.web.rest;

import com.vuha.repository.UserRepository;
import com.vuha.service.UserService;
import com.vuha.web.rest.vm.UserVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserResource {

    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/public/users")
    ResponseEntity<List<UserVM>> searchUser(@RequestParam(required = false) String field,
                                            @RequestParam(required = false) String keyword) {
        List<UserVM> users = userService.search(field, keyword);
        return ResponseEntity.ok(users);
    }

}
