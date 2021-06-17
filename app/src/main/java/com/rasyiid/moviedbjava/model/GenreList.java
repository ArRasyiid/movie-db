package com.rasyiid.moviedbjava.model;

import java.io.Serializable;
import java.util.List;

public class GenreList implements Serializable {
    private List<Genre> genres;

    public GenreList() {
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}
