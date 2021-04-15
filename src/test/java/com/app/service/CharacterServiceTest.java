package com.app.service;

import com.app.model.Character;
import com.app.model.*;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CharacterServiceTest {

    private StoriesService storiesService = mock(StoriesService.class);
    private MarvelHttpClient marvelHttpClient = mock(MarvelHttpClient.class);
    private Gson gson = new Gson();

    @InjectMocks
    private CharacterService target = new CharacterService(marvelHttpClient, gson, storiesService);

    @Test
    public void should_return_comicsAndEvents_for_single_character() {
        String[] singleCharacter = {"Hulk"};
        DataWrapper<CharacterComicAndEvent> expectedObject = getExpectedObject();
        ResponseEntity<String> response = ResponseEntity.of(Optional.of(gson.toJson(expectedObject)));
        when(marvelHttpClient.sendGetRequest(eq("http://gateway.marvel.com/v1/public/characters"), any())).thenReturn(response);

        List<ComicAndEvent> comicsAndEvents = target.findComicsAndEvents(singleCharacter);

        assertThat(comicsAndEvents).hasSize(1);
        assertThat(comicsAndEvents.get(0).getComicsCount()).isEqualTo(1);
        assertThat(comicsAndEvents.get(0).getEventCount()).isEqualTo(2);
    }

    private DataWrapper<CharacterComicAndEvent> getExpectedObject() {
        List<CharacterComicAndEvent> characterComicsAndEvents = Collections.singletonList(new CharacterComicAndEvent(new ComicCount(1), new EventCount(2)));
        return new DataWrapper<>("text", new DataContainer<>(characterComicsAndEvents, 0, 0));
    }

    @Test
    public void should_return_1_commonStory() {
        int hulkId = 123;
        Characters commonCharacters = new Characters(Arrays.asList(new CharacterItem("Hulk"), new CharacterItem("Thor")));
        Characters nonCommonCharacters = new Characters(Collections.singletonList(new CharacterItem("Hulk")));
        List<Story> hulkStories = Arrays.asList(new Story(0, "commonStory", commonCharacters), new Story(0, "hulkStory", nonCommonCharacters));
        ResponseEntity<String> responseWithHulkId = ResponseEntity.of(Optional.of(gson.toJson(getExpectedCharacterForId(hulkId))));
        when(marvelHttpClient.sendGetRequest(eq("http://gateway.marvel.com/v1/public/characters"), any())).thenReturn(responseWithHulkId);
        when(storiesService.getAllStoriesOfCharacter(hulkId)).thenReturn(hulkStories);

        List<Story> commonStories = target.findCommonStories("Hulk", "Thor");

        assertThat(commonStories).hasSize(1);
        assertThat(commonStories.get(0).getTitle()).isEqualTo("commonStory");
    }

    private DataWrapper<Character> getExpectedCharacterForId(int hulkId) {
        List<Character> character = Collections.singletonList(new Character(hulkId));
        return new DataWrapper<>("text", new DataContainer<>(character, 0, 0));
    }
}