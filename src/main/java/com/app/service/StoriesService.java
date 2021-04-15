package com.app.service;

import com.app.model.DataWrapper;
import com.app.model.Story;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
public class StoriesService {

    private final MarvelHttpClient marvelHttpClient;
    private final Gson gson;

    public StoriesService(MarvelHttpClient marvelHttpClient, Gson gson) {
        this.marvelHttpClient = marvelHttpClient;
        this.gson = gson;
    }

    public List<Story> getAllStoriesOfCharacter(int characterId) {
        List<Story> allStories = new LinkedList<>();
        int currentOffset = 0;
        int count;
        do {
            DataWrapper<Story> loadedDataWrapperStories = getUpTo100StoriesOfCharacter(characterId, currentOffset);
            allStories.addAll(loadedDataWrapperStories.getData().getResults());
            count = loadedDataWrapperStories.getData().getCount();
            currentOffset += 100;
        } while (count > 0);

        return allStories;
    }

    private DataWrapper<Story> getUpTo100StoriesOfCharacter(int characterId, int offset) {
        MultiValueMap<String, String> additionalParams = new LinkedMultiValueMap<>();
        additionalParams.put("limit", Collections.singletonList("100"));
        additionalParams.put("offset", Collections.singletonList(String.valueOf(offset)));
        ResponseEntity<String> response = marvelHttpClient.sendGetRequest(String.format("http://gateway.marvel.com/v1/public/characters/%s/stories", characterId), additionalParams);
        Type dataType = new TypeToken<DataWrapper<Story>>() {}.getType();
        return gson.fromJson(response.getBody(), dataType);
    }
}
