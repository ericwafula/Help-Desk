package com.moringaschool.helpdesk.models;

public class PostQuestion {
    private String category;
    private String title;
    private String body;
    private String language;

    public PostQuestion(String category, String title, String body, String language) {
        this.title = title;
        this.body = body;
        this.category = category;
        this.language = language;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
