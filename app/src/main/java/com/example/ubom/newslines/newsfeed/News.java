package com.example.ubom.newslines.newsfeed;

/**
 * Created by ubom on 7/24/18
 * */
public class News {

    String title;
    String author;
    String url;
    String date;
    String section;

    //News constructor
    public News(String title, String author, String url, String date, String section) {
        this.title = title;
        this.author = author;
        this.url = url;
        this.date = date;
        this.section = section;
    }

    //returns string
    public String getTitle() {
        return title;
    }

    //set title of string
    public void setTitle(String title) {
        this.title = title;
    }

    //gets author string
    public String getAuthor() {
        return author;
    }

    //sets author string
    public void setAuthor(String author) {
        this.author = author;
    }

    //returns url string
    public String getUrl() {
        return url;
    }

    //sets url
    public void setUrl(String url) {
        this.url = url;
    }

    //returns date
    public String getDate() {
        return date;
    }

    //sets date
    public void setDate(String date) {
        this.date = date;
    }

    //gets public string "section"
    public String getSection() {
        return section;
    }

    //sets string section
    public void setSection(String section) {
        this.section = section;
    }

    //aggregates string, returns "news"
    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", url='" + url + '\'' +
                ", date='" + date + '\'' +
                ", section='" + section + '\'' +
                '}';
    }
}
