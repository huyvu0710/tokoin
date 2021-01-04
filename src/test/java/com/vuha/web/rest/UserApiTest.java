package com.vuha.web.rest;

import com.vuha.TokoinApp;
import com.vuha.service.UserService;
import com.vuha.web.rest.errors.ExceptionTranslator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(classes = TokoinApp.class)
public class UserApiTest {

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    UserService userService;

    private MockMvc mockMvc;

    private static final String USER_URL = "/api/public/users";

    @BeforeEach
    public void setup() {
        final UserResource userResource = new UserResource(userService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userResource)
                .setControllerAdvice(exceptionTranslator)
                .build();
    }

    @Test
    public void testSearchUserWithoutFieldAndKeyword() throws Exception {
        mockMvc.perform(get(USER_URL)
            .param("field", "")
            .param("keyword", "")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$[0].assignee_ticket_subjects").exists())
            .andExpect(jsonPath("$[0].submitted_ticket_subjects").exists())
            .andExpect(jsonPath("$[0].organization_name").exists());
    }

    @Test
    public void testSearchUserWithField() throws Exception {
        List<Map<String, String>> testcaseList = new ArrayList<>();

        Map<String, String> tc1 = new HashMap<>();
        tc1.put("field", "_id");
        tc1.put("keyword", "1");
        tc1.put("expect", "1");
        testcaseList.add(tc1);

        Map<String, String> tc2 = new HashMap<>();
        tc2.put("field", "url");
        tc2.put("keyword", "http://initech.tokoin.io.com/api/v2/users/1.json");
        tc2.put("expect", "1");
        testcaseList.add(tc2);

        Map<String, String> tc3 = new HashMap<>();
        tc3.put("field", "external_id");
        tc3.put("keyword", "74341f74-9c79-49d5-9611-87ef9b6eb75f");
        tc3.put("expect", "1");
        testcaseList.add(tc3);

        Map<String, String> tc4 = new HashMap<>();
        tc4.put("field", "name");
        tc4.put("keyword", "Francisca Rasmussen");
        tc4.put("expect", "1");
        testcaseList.add(tc4);

        Map<String, String> tc5 = new HashMap<>();
        tc5.put("field", "alias");
        tc5.put("keyword", "Miss Coffey");
        tc5.put("expect", "1");
        testcaseList.add(tc5);

        Map<String, String> tc6 = new HashMap<>();
        tc6.put("field", "created_at");
        tc6.put("keyword", "2016-04-15T05:19:46 -10:00");
        tc6.put("expect", "1");
        testcaseList.add(tc6);

        Map<String, String> tc7 = new HashMap<>();
        tc7.put("field", "email");
        tc7.put("keyword", "coffeyrasmussen@flotonic.com");
        tc7.put("expect", "1");
        testcaseList.add(tc7);

        Map<String, String> tc8 = new HashMap<>();
        tc8.put("field", "phone");
        tc8.put("keyword", "8335-422-718");
        tc8.put("expect", "1");
        testcaseList.add(tc8);

        for (Map<String, String> testcase : testcaseList) {
            String field = testcase.get("field");
            String keyword = testcase.get("keyword");
            String expect = testcase.get("expect");

            mockMvc.perform(get(USER_URL)
                .param("field", field)
                .param("keyword", keyword)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].assignee_ticket_subjects").exists())
                .andExpect(jsonPath("$[0].submitted_ticket_subjects").exists())
                .andExpect(jsonPath("$[0].organization_name").exists())
                .andExpect(jsonPath("$[0]._id").value(expect));
        }
    }
}
