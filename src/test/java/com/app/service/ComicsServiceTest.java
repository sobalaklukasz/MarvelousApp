package com.app.service;

import com.app.model.*;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ComicsServiceTest {

    private MarvelHttpClient marvelHttpClient = mock(MarvelHttpClient.class);
    private Gson gson = new Gson();

    @InjectMocks
    private ComicsService target = new ComicsService(marvelHttpClient, gson);

    @Test
    public void should_return_comic() {
        int comicId = 1;
        DataWrapper<Comic> expectedObject = getExpectedObject();
        ResponseEntity<String> response = ResponseEntity.of(Optional.of(gson.toJson(expectedObject)));
        when(marvelHttpClient.sendGetRequest(eq("http://gateway.marvel.com/v1/public/comics/" + comicId))).thenReturn(response);

        DataWrapper<Comic> comic = target.getComic(comicId);

        assertThat(comic.getAttributionText()).isEqualTo("SomeAttributionText");
        assertThat(comic.getData().getResults()).hasSize(1);
        Comic actualComic = comic.getData().getResults().get(0);
        assertThat(actualComic.getCharacters().getItems().get(0).getName()).isEqualTo("Hulk");
        assertThat(actualComic.getDescription()).isEqualTo("Some Description");
    }

    private DataWrapper<Comic> getExpectedObject() {
        List<Comic> comic = Collections.singletonList(new Comic(
                0,
                "title",
                null,
                null,
                "Some Description",
                new Characters(Collections.singletonList(new CharacterItem("Hulk")))));
        return new DataWrapper<>("SomeAttributionText", new DataContainer<>(comic, 0, 0));
    }
}