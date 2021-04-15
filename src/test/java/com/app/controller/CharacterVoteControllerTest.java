package com.app.controller;

import com.app.dao.CharacterVoteRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CharacterVoteController.class)
public class CharacterVoteControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CharacterVoteRepository characterVoteRepository;

    @Test
    public void shouldNotFindTicketBecauseOfLocation() throws Exception {
        this.mockMvc.perform(get("/characterVote/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Voted for id: 1"))
                .andReturn();
    }

}