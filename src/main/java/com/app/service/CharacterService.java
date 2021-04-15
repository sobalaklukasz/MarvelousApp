package com.app.service;

import com.app.model.*;
import com.app.model.Character;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;

import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CharacterService {

    private final MarvelHttpClient marvelHttpClient;
    private final Gson gson;
    private final StoriesService storiesService;

    public CharacterService(MarvelHttpClient marvelHttpClient, Gson gson, StoriesService storiesService) {
        this.marvelHttpClient = marvelHttpClient;
        this.gson = gson;
        this.storiesService = storiesService;
    }

    public List<Story> findCommonStories(String firstCharacter, String... otherCharacters) {

        return storiesService.getAllStoriesOfCharacter(getCharacterId(firstCharacter)).stream()
                .filter(story -> characterItemsContainsAllOtherCharacters(story.getCharacters().getItems(), otherCharacters))
                .collect(Collectors.toList());
    }

    private int getCharacterId(String characterName) {
        LinkedMultiValueMap<String, String> objectObjectLinkedMultiValueMap = new LinkedMultiValueMap<>();
        objectObjectLinkedMultiValueMap.add("name", characterName);
        ResponseEntity<String> response = marvelHttpClient.sendGetRequest("http://gateway.marvel.com/v1/public/characters", objectObjectLinkedMultiValueMap);
        Type dataType = new TypeToken<DataWrapper<Character>>() {}.getType();
        DataWrapper<Character> characterDataWrapper = gson.fromJson(response.getBody(), dataType);
        return characterDataWrapper.getData().getResults().get(0).getId();
    }

    private boolean characterItemsContainsAllOtherCharacters(List<CharacterItem> characterItems, String[] otherCharacters) {
        return characterItems.stream().map(CharacterItem::getName).collect(Collectors.toList()).containsAll(Arrays.asList(otherCharacters));
    }

    public List<ComicAndEvent> findComicsAndEvents(String[] characterNames) {
        return Arrays.stream(characterNames)
                .map(this::getComicsAndEvents)
                .collect(Collectors.toList());
    }

    private ComicAndEvent getComicsAndEvents(String characterName) {
        LinkedMultiValueMap<String, String> objectObjectLinkedMultiValueMap = new LinkedMultiValueMap<>();
        objectObjectLinkedMultiValueMap.add("name", characterName);
        ResponseEntity<String> response = marvelHttpClient.sendGetRequest("http://gateway.marvel.com/v1/public/characters", objectObjectLinkedMultiValueMap);
        Type dataType = new TypeToken<DataWrapper<CharacterComicAndEvent>>() {}.getType();
        DataWrapper<CharacterComicAndEvent> characterComicAndEvent = gson.fromJson(response.getBody(), dataType);
        return new ComicAndEvent(
                characterName,
                characterComicAndEvent.getData().getResults().get(0).getComics().getAvailable(),
                characterComicAndEvent.getData().getResults().get(0).getEvents().getAvailable());
    }
}
