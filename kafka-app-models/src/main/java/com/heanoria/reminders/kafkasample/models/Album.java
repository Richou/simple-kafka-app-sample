package com.heanoria.reminders.kafkasample.models;

import java.io.Serializable;

public class Album implements Serializable {

    private String title;
    private String artist;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "Album{" +
                "title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                '}';
    }
}
