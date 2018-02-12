package com.heanoria.reminders.kafkasample.models;

import java.util.List;

public class Albums {

    private List<Album> albums;

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public Album get(int index) {
        if (index < albums.size()) {
            return this.albums.get(index);
        }
        return null;
    }

    public int getSize() {
        return albums.size();
    }
}
