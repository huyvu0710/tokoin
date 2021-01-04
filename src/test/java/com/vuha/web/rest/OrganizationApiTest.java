package com.vuha.web.rest;

import com.vuha.TokoinApp;
import com.vuha.service.OrganizationService;
import com.vuha.web.rest.errors.ExceptionTranslator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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

import static org.hamcrest.Matchers.hasValue;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(classes = TokoinApp.class)
public class OrganizationApiTest {

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    OrganizationService organizationService;

    private MockMvc mockMvc;

    private static final String ORGANIZATION_URL = "/api/public/organizations";

    @BeforeEach
    public void setup() {
        final OrganizationResource organizationResource = new OrganizationResource(organizationService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(organizationResource)
                .setControllerAdvice(exceptionTranslator)
                .build();
    }

    @Test
    public void testSearchOrganizationWithoutFieldAndKeyword() throws Exception {
        mockMvc.perform(get(ORGANIZATION_URL)
            .param("field", "")
            .param("keyword", "")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$[0].ticket_subjects").exists())
            .andExpect(jsonPath("$[0].user_names").exists());
    }

    @Test
    public void testSearchOrganizationWithField() throws Exception {
        List<Map<String, String>> testcaseList = new ArrayList<>();

        Map<String, String> tc1 = new HashMap<>();
        tc1.put("field", "_id");
        tc1.put("keyword", "101");
        tc1.put("expect", "101");
        testcaseList.add(tc1);

        Map<String, String> tc2 = new HashMap<>();
        tc2.put("field", "url");
        tc2.put("keyword", "http://initech.tokoin.io.com/api/v2/organizations/101.json");
        tc2.put("expect", "101");
        testcaseList.add(tc2);

        Map<String, String> tc3 = new HashMap<>();
        tc3.put("field", "external_id");
        tc3.put("keyword", "9270ed79-35eb-4a38-a46f-35725197ea8d");
        tc3.put("expect", "101");
        testcaseList.add(tc3);

        Map<String, String> tc4 = new HashMap<>();
        tc4.put("field", "name");
        tc4.put("keyword", "Enthaze");
        tc4.put("expect", "101");
        testcaseList.add(tc4);

        Map<String, String> tc5 = new HashMap<>();
        tc5.put("field", "domain_names");
        tc5.put("keyword", "zentix.com");
        tc5.put("expect", "101");
        testcaseList.add(tc5);

        Map<String, String> tc6 = new HashMap<>();
        tc6.put("field", "created_at");
        tc6.put("keyword", "2016-05-21T11:10:28 -10:00");
        tc6.put("expect", "101");
        testcaseList.add(tc6);

        Map<String, String> tc7 = new HashMap<>();
        tc7.put("field", "details");
        tc7.put("keyword", "MegaCorp");
        tc7.put("expect", "101");
        testcaseList.add(tc7);

        Map<String, String> tc8 = new HashMap<>();
        tc8.put("field", "shared_tickets");
        tc8.put("keyword", "false");
        tc8.put("expect", "101");
        testcaseList.add(tc8);

        Map<String, String> tc9 = new HashMap<>();
        tc9.put("field", "tags");
        tc9.put("keyword", "West");
        tc9.put("expect", "101");
        testcaseList.add(tc9);

        for (Map<String, String> testcase : testcaseList) {
            String field = testcase.get("field");
            String keyword = testcase.get("keyword");
            String expect = testcase.get("expect");

            mockMvc.perform(get(ORGANIZATION_URL)
                .param("field", field)
                .param("keyword", keyword)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].ticket_subjects").exists())
                .andExpect(jsonPath("$[0].user_names").exists())
                .andExpect(jsonPath("$[0]._id").value(expect));
        }
    }
}
