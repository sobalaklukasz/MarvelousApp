package com.app.service;

import com.app.model.DataContainer;
import com.app.model.DataWrapper;
import com.app.model.Story;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StoriesServiceTest {

    private MarvelHttpClient marvelHttpClient = mock(MarvelHttpClient.class);
    private Gson gson = new Gson();

    @InjectMocks
    private StoriesService target = new StoriesService(marvelHttpClient, gson);

    @Test
    public void should_return_stories() {
        int characterId = 1;
        DataWrapper<Story> expectedObject = getExpectedObject();
        ResponseEntity<String> response = ResponseEntity.of(Optional.of(gson.toJson(expectedObject)));
        when(marvelHttpClient.sendGetRequest(eq("http://gateway.marvel.com/v1/public/characters/" + characterId + "/stories"), any())).thenReturn(response);

        List<Story> stories = target.getAllStoriesOfCharacter(characterId);

        assertThat(stories).hasSize(1);
        assertThat(stories.get(0).getTitle()).isEqualTo("title");
    }

    private DataWrapper<Story> getExpectedObject() {
        List<Story> stories = Collections.singletonList(new Story(0, "title", null));
        return new DataWrapper<>("SomeAttributionText", new DataContainer<>(stories, 0, 2));
    }
}