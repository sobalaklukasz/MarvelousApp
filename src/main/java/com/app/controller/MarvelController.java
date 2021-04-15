package com.app.controller;

import com.app.model.Comic;
import com.app.model.DataWrapper;
import com.app.model.Image;
import com.app.service.ComicsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MarvelController {

    private static final int HULK_COMIC_ID = 27649;

    private final ComicsService comicsService;

    public MarvelController(ComicsService comicsService) {
        this.comicsService = comicsService;
    }

    @GetMapping("/story")
    public String sendHulkStory(Model model) {
        DataWrapper<Comic> comic = comicsService.getComic(HULK_COMIC_ID);
        Image image = comic.getData().getResults().get(0).getImages().get(0);

        model.addAttribute("attributionText", comic.getAttributionText());
        model.addAttribute("story", comic.getData().getResults().get(0).getDescription());
        model.addAttribute("image", image.getPath() + "." + image.getExtension());
        model.addAttribute("name", comic.getData().getResults().get(0).getCharacters().getItems().get(0).getName());

        return "StoryPage";
    }
}
