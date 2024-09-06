package com.example;

public class Song {
    private String title;
    private double duration;
    private String filePath;  // Path to the song file

    public Song(String title, double duration, String filePath) {
        this.title = title;
        this.duration = duration;
        this.filePath = filePath;
    }

    public String getTitle() {
        return title;
    }

    public double getDuration() {
        return duration;
    }

    public String getFilePath() {
        return filePath;
    }

    @Override
    public String toString() {
        return "Song{" +
                "title='" + title + '\'' +
                ", duration=" + duration +
                '}';
    }
}
