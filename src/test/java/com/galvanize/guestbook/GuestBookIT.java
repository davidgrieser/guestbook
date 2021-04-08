package com.galvanize.guestbook;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Transactional
public class GuestBookIT {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @Test
    void getRootPath() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().stringValues("Location", "/docs/index.html"));
    }
    @Test
    void getZeroEntries() throws Exception {
        mockMvc.perform(get("/entries"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("length()").value(0))
                .andDo(document("Get-Entries-Empty"));
    }

    @Test
    void postEntry() throws Exception {
        EntryDto entryDto = new EntryDto("David", "Making sure this works");

        mockMvc.perform(post("/entries")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(entryDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("name").value("David"))
                .andExpect(jsonPath("comment").value("Making sure this works"))
                .andDo(document("Post-Entries", responseFields(
                        fieldWithPath("name").description("Name of person who left comment"),
                        fieldWithPath("comment").description("Comment left by person")
                )));
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
                .andExpect(jsonPath("[1].name").value("Wes"))
                .andDo(document("Get-Entries", responseFields(
                        fieldWithPath("[0].name").description("Name of person who left comment"),
                        fieldWithPath("[0].comment").description("Comment left by person")
                )));
    }
}
