package com.rasyiid.moviedbjava.model;

import java.io.Serializable;
import java.util.List;

public class Review implements Serializable {
    private String author;
    private AuthorDetails author_details;
    private String content;
    private String created_at;
    private String id;
    private String updated_at;
    private String url;

    public Review() {
    }

    public String getAuthor() {
        return author;
    }

    public AuthorDetails getAuthor_details() {
        return author_details;
    }

    public void setAuthor_details(AuthorDetails author_details) {
        this.author_details = author_details;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
