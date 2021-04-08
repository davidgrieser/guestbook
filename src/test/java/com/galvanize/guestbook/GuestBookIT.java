package com.galvanize.guestbook;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GuestBookIT {

    @Autowired
    MockMvc mockMvc;

    @Test
    void getZeroEntries() throws Exception {
        mockMvc.perform(get("/entries"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("length()").value(0));
    }

    @Test
    void postEntry() throws Exception {
        mockMvc.perform(post("/entries")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"David\", \"comment\":\"Making sure this works\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("name").value("David"))
                .andExpect(jsonPath("comment").value("Making sure this works"));
    }
}