package com.app.service;

import com.app.model.Comic;
import com.app.model.DataWrapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;

@Service
public class ComicsService {

    private final MarvelHttpClient marvelHttpClient;
    private final Gson gson;

    public ComicsService(MarvelHttpClient marvelHttpClient, Gson gson) {
        this.marvelHttpClient = marvelHttpClient;
        this.gson = gson;
    }

    public DataWrapper<Comic> getComic(int comicId) {
        ResponseEntity<String> response = marvelHttpClient.sendGetRequest("http://gateway.marvel.com/v1/public/comics/" + comicId);
        Type dataType = new TypeToken<DataWrapper<Comic>>() {}.getType();
        return gson.fromJson(response.getBody(), dataType);
    }

}
