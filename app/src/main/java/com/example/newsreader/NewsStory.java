package com.example.newsreader;

import java.util.List;

public class NewsStory {

    private int id;
    private boolean deleted;
    private String type;
    private String by;
    private int time;
    private String text;
    private boolean dead;
    private int parent;
    private int poll;
    private List<Integer> commentsList;
    private String url;
    private int score;
    private String title;
    private List<Integer> partsList;
    private int commentCount;

    public NewsStory(){
    }

    public NewsStory(int id, boolean deleted, String type, String by, int time, String text, boolean dead, int parent, int poll, List<Integer> commentsList, String url, int score, String title, List<Integer> partsList, int commentCount) {
        this.id = id;
        this.deleted = deleted;
        this.type = type;
        this.by = by;
        this.time = time;
        this.text = text;
        this.dead = dead;
        this.parent = parent;
        this.poll = poll;
        this.commentsList = commentsList;
        this.url = url;
        this.score = score;
        this.title = title;
        this.partsList = partsList;
        this.commentCount = commentCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
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

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public int getPoll() {
        return poll;
    }

    public void setPoll(int poll) {
        this.poll = poll;
    }

    public List<Integer> getCommentsList() {
        return commentsList;
    }

    public void setCommentsList(List<Integer> commentsList) {
        this.commentsList = commentsList;
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

    public List<Integer> getPartsList() {
        return partsList;
    }

    public void setPartsList(List<Integer> partsList) {
        this.partsList = partsList;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }
}