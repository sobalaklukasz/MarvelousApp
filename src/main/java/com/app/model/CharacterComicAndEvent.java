package com.app.model;

public class CharacterComicAndEvent {

    private ComicCount comics;
    private EventCount events;

    public CharacterComicAndEvent(ComicCount comics, EventCount events) {
        this.comics = comics;
        this.events = events;
    }

    public ComicCount getComics() {
        return comics;
    }

    public EventCount getEvents() {
        return events;
    }
}
