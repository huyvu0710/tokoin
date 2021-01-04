package com.vuha.web.rest;

import com.vuha.TokoinApp;
import com.vuha.service.TicketService;
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
public class TicketApiTest {

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    TicketService ticketService;

    private MockMvc mockMvc;

    private static final String TICKET_URL = "/api/public/tickets";

    @BeforeEach
    public void setup() {
        final TicketResource ticketResource = new TicketResource(ticketService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(ticketResource)
                .setControllerAdvice(exceptionTranslator)
                .build();
    }

    @Test
    public void testSearchTicketWithoutFieldAndKeyword() throws Exception {
        mockMvc.perform(get(TICKET_URL)
            .param("field", "")
            .param("keyword", "")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$[0].organization_name").exists())
            .andExpect(jsonPath("$[0].assignee_name").exists())
            .andExpect(jsonPath("$[0].submitter_name").exists());
    }

    @Test
    public void testSearchTicketWithField() throws Exception {
        List<Map<String, String>> testcaseList = new ArrayList<>();

        Map<String, String> tc1 = new HashMap<>();
        tc1.put("field", "_id");
        tc1.put("keyword", "436bf9b0-1147-4c0a-8439-6f79833bff5b");
        tc1.put("expect", "436bf9b0-1147-4c0a-8439-6f79833bff5b");
        testcaseList.add(tc1);

        Map<String, String> tc2 = new HashMap<>();
        tc2.put("field", "url");
        tc2.put("keyword", "http://initech.tokoin.io.com/api/v2/tickets/436bf9b0-1147-4c0a-8439-6f79833bff5b.json");
        tc2.put("expect", "436bf9b0-1147-4c0a-8439-6f79833bff5b");
        testcaseList.add(tc2);

        Map<String, String> tc3 = new HashMap<>();
        tc3.put("field", "external_id");
        tc3.put("keyword", "9210cdc9-4bee-485f-a078-35396cd74063");
        tc3.put("expect", "436bf9b0-1147-4c0a-8439-6f79833bff5b");
        testcaseList.add(tc3);

        Map<String, String> tc4 = new HashMap<>();
        tc4.put("field", "subject");
        tc4.put("keyword", "A Catastrophe in Korea (North)");
        tc4.put("expect", "436bf9b0-1147-4c0a-8439-6f79833bff5b");
        testcaseList.add(tc4);

        for (Map<String, String> testcase : testcaseList) {
            String field = testcase.get("field");
            String keyword = testcase.get("keyword");
            String expect = testcase.get("expect");

            mockMvc.perform(get(TICKET_URL)
                .param("field", field)
                .param("keyword", keyword)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].organization_name").exists())
                .andExpect(jsonPath("$[0].assignee_name").exists())
                .andExpect(jsonPath("$[0].submitter_name").exists())
                .andExpect(jsonPath("$[0]._id").value(expect));
        }
    }
}
