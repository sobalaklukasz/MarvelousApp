package com.app.model;

public class Story {
    private int id;
    private String title;
    private Characters characters;

    public Story(int id, String title, Characters characters) {
        this.id = id;
        this.title = title;
        this.characters = characters;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Characters getCharacters() {
        return characters;
    }

}
