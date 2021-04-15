package com.app.model;

public class ComicAndEvent {

    private String name;
    private int comicsCount;
    private int eventCount;

    public ComicAndEvent(String name, int comicsCount, int eventsCount) {
        this.name = name;
        this.comicsCount = comicsCount;
        this.eventCount = eventsCount;
    }

    public String getName() {
        return name;
    }

    public int getComicsCount() {
        return comicsCount;
    }

    public int getEventCount() {
        return eventCount;
    }
}
