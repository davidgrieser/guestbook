package com.galvanize.guestbook;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class GuestBookIT {

    @Autowired
    ObjectMapper objectMapper;

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
        EntryDto entryDto = new EntryDto("David", "Making sure this works");

        mockMvc.perform(post("/entries")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(entryDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("name").value("David"))
                .andExpect(jsonPath("comment").value("Making sure this works"));
    }

    @Test
    void postEntries() throws Exception {
        EntryDto firstEntry = new EntryDto("David", "Making sure this works");
        EntryDto secondEntry = new EntryDto("Wes", "Saying hello!");

        mockMvc.perform(post("/entries")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(firstEntry)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("name").value("David"))
                .andExpect(jsonPath("comment").value("Making sure this works"));
        mockMvc.perform(post("/entries")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(secondEntry)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("name").value("Wes"))
                .andExpect(jsonPath("comment").value("Saying hello!"));
        mockMvc.perform(get("/entries"))
                .andExpect(jsonPath("length()").value(2))
                .andExpect(jsonPath("[0].name").value("David"))
                .andExpect(jsonPath("[0].comment").value("Making sure this works"))
                .andExpect(jsonPath("[1].name").value("Wes"));
    }
}
