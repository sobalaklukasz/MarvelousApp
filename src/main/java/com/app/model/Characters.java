package com.app.model;

import java.util.List;

public class Characters {

    private List<CharacterItem> items;

    public Characters(List<CharacterItem> items) {
        this.items = items;
    }

    public List<CharacterItem> getItems() {
        return items;
    }
}
