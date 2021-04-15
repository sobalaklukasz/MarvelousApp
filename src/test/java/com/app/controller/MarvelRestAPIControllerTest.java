package com.app.controller;

import com.app.dao.CharacterVoteRepository;
import com.app.model.ComicAndEvent;
import com.app.model.Story;
import com.app.service.CharacterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MarvelRestAPI.class)
public class MarvelRestAPIControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CharacterService characterService;

    @Test
    public void should_return_common_stories() throws Exception {
        List<Story> commonStories = Collections.singletonList(new Story(0, "someTitle", null));
        when(characterService.findCommonStories("Hulk", "Thor")).thenReturn(commonStories);
        this.mockMvc.perform(get("/characters/Hulk,Thor/stories").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"id\":0,\"title\":\"someTitle\",\"characters\":null}]"))
                .andReturn();
    }

    @Test
    public void should_return_comics_and_events() throws Exception {
        List<ComicAndEvent> comicsAndEvents = Collections.singletonList(new ComicAndEvent("Thor", 6, 6));
        when(characterService.findComicsAndEvents(Collections.singletonList("Thor").toArray(new String[1]))).thenReturn(comicsAndEvents);
        this.mockMvc.perform(get("/characters/Thor/comicsAndEvents").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"name\":\"Thor\",\"comicsCount\":6,\"eventCount\":6}]"))
                .andReturn();

    }

}