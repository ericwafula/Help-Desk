
package com.moringaschool.helpdesk.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("screenshot")
    @Expose
    private String screenshot;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Result() {
    }

    /**
     * 
     * @param date
     * @param language
     * @param id
     * @param screenshot
     * @param category
     * @param title
     * @param body
     */
    public Result(Integer id, String date, String category, String title, String body, String language, String screenshot) {
        super();
        this.id = id;
        this.date = date;
        this.category = category;
        this.title = title;
        this.body = body;
        this.language = language;
        this.screenshot = screenshot;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.date == null)? 0 :this.date.hashCode()));
        result = ((result* 31)+((this.language == null)? 0 :this.language.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.screenshot == null)? 0 :this.screenshot.hashCode()));
        result = ((result* 31)+((this.category == null)? 0 :this.category.hashCode()));
        result = ((result* 31)+((this.title == null)? 0 :this.title.hashCode()));
        result = ((result* 31)+((this.body == null)? 0 :this.body.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Result) == false) {
            return false;
        }
        Result rhs = ((Result) other);
        return ((((((((this.date == rhs.date)||((this.date!= null)&&this.date.equals(rhs.date)))&&((this.language == rhs.language)||((this.language!= null)&&this.language.equals(rhs.language))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.screenshot == rhs.screenshot)||((this.screenshot!= null)&&this.screenshot.equals(rhs.screenshot))))&&((this.category == rhs.category)||((this.category!= null)&&this.category.equals(rhs.category))))&&((this.title == rhs.title)||((this.title!= null)&&this.title.equals(rhs.title))))&&((this.body == rhs.body)||((this.body!= null)&&this.body.equals(rhs.body))));
    }

}
