package com.app.model;

import java.util.List;

public class Comic {

    private int id;
    private String title;
    private Image thumbnail;
    private List<Image> images;
    private String description;
    private Characters characters;

    public Comic(int id, String title, Image thumbnail, List<Image> images, String description, Characters characters) {
        this.id = id;
        this.title = title;
        this.thumbnail = thumbnail;
        this.images = images;
        this.description = description;
        this.characters = characters;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Image getThumbnail() {
        return thumbnail;
    }

    public List<Image> getImages() {
        return images;
    }

    public String getDescription() {
        return description;
    }

    public Characters getCharacters() {
        return characters;
    }
}
