package com.example.newsreader;

import com.google.gson.annotations.SerializedName;

public class NewsStory {

    @SerializedName("id")
    private int id;
    @SerializedName("type")
    private String type;
    @SerializedName("by")
    private String by;
    @SerializedName("time")
    private int time;
    @SerializedName("text")
    private String text;
    @SerializedName("url")
    private String url;
    @SerializedName("score")
    private int score;
    @SerializedName("title")
    private String title;

    public NewsStory(){
    }

    public NewsStory(int id, String type, String by, int time, String text, String url, int score, String title) {
        this.id = id;
        this.type = type;
        this.by = by;
        this.time = time;
        this.text = text;
        this.url = url;
        this.score = score;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }
}