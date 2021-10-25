package com.moringaschool.helpdesk.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostQuestion {
    @Expose
    @SerializedName("category")
    public String category;
    @Expose
    @SerializedName("title")
    public String title;
    @Expose
    @SerializedName("body")
    public String body;
    @Expose
    @SerializedName("language")
    public String language;
    @Expose
    @SerializedName("screenshot")
    public String screenshot;

    public PostQuestion(String category, String title, String body, String language, String screenshot) {
        this.title = title;
        this.body = body;
        this.category = category;
        this.language = language;
        this.screenshot = screenshot;
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

    public String getScreenshot() {
        return screenshot;
    }

    public void setScreenshot(String screenshot) {
        this.screenshot = screenshot;
    }
}
