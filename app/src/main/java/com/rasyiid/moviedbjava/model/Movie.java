package com.rasyiid.moviedbjava.model;

import android.os.Parcel;

public class Movie extends Result {
    private String original_title;
    private String title;

    public Movie() {
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
