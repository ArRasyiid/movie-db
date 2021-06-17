package com.rasyiid.moviedbjava.model;

public class Result {
    private Integer id;
    private String original_language;//the format sample is "en", "id", etc
    private Double popularity;
    private Integer vote_count;
    private String poster_path;//the format sample is "/xBHvZcjRiWyobQ9kxBhO6B2dtRI.jpg"
    private String backdrop_path;//the format sample same as poster_path
    private Integer[] genre_ids;
    private String overview;
    private Double vote_average;

    public Result() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public Integer getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public Integer[] getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(Integer[] genre_ids) {
        this.genre_ids = genre_ids;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }
}
