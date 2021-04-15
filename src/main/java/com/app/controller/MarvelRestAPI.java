package com.app.controller;

import com.app.model.ComicAndEvent;
import com.app.model.Story;
import com.app.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController()
public class MarvelRestAPI {

    private final CharacterService characterService;

    public MarvelRestAPI(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping("/characters/{comaSeparatedCharacterNames}/stories")
    public List<Story> sendSharedStories(@PathVariable String comaSeparatedCharacterNames) {
        List<String> names = Arrays.asList(comaSeparatedCharacterNames.split(","));
        return characterService.findCommonStories(names.get(0), names.subList(1, names.size()).toArray(new String[1]));
    }

    @GetMapping("/characters/{comaSeparatedCharacterNames}/comicsAndEvents")
    public List<ComicAndEvent> sendComicsAndEvents(@PathVariable String comaSeparatedCharacterNames) {
        return characterService.findComicsAndEvents(comaSeparatedCharacterNames.split(","));
    }

}
