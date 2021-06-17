package com.rasyiid.moviedbjava.model;

import com.rasyiid.moviedbjava.model.Genre;
import com.rasyiid.moviedbjava.model.Movie;

import java.io.Serializable;
import java.util.ArrayList;

public class MovieDetails implements Serializable {

    private Boolean adult;
    private String backdrop_path;
    private Movie belongs_to_collection;
    private Long budget;
    private ArrayList<Genre> genres;
    private String homepage;
    private Long id;
    private String imdb_id;
    private String original_language;
    private String original_title;
    private String overview;
    private Double popularity;
    private String poster_path;
    private ArrayList<ProductionCompany> production_companies;
    private ArrayList<ProductionCountry> production_countries;
    private String release_date;
    private Integer revenue;
    private Integer runtime;
    private ArrayList<SpokenLanguage> spoken_languages;
    private String status;
    private String tagline;
    private String title;
    private Boolean video;
    private Float vote_average;
    private Integer vote_count;

    public MovieDetails() { }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public Movie getBelongs_to_collection() {
        return belongs_to_collection;
    }

    public void setBelongs_to_collection(Movie belongs_to_collection) {
        this.belongs_to_collection = belongs_to_collection;
    }

    public Long getBudget() {
        return budget;
    }

    public void setBudget(Long budget) {
        this.budget = budget;
    }

    public ArrayList<Genre> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<Genre> genres) {
        this.genres = genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public ArrayList<ProductionCompany> getProduction_companies() {
        return production_companies;
    }

    public void setProduction_companies(ArrayList<ProductionCompany> production_companies) {
        this.production_companies = production_companies;
    }

    public ArrayList<ProductionCountry> getProduction_countries() {
        return production_countries;
    }

    public void setProduction_countries(ArrayList<ProductionCountry> production_countries) {
        this.production_countries = production_countries;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public Integer getRevenue() {
        return revenue;
    }

    public void setRevenue(Integer revenue) {
        this.revenue = revenue;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public ArrayList<SpokenLanguage> getSpoken_languages() {
        return spoken_languages;
    }

    public void setSpoken_languages(ArrayList<SpokenLanguage> spoken_languages) {
        this.spoken_languages = spoken_languages;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public Float getVote_average() {
        return vote_average;
    }

    public void setVote_average(Float vote_average) {
        this.vote_average = vote_average;
    }

    public Integer getVote_count() {
        return vote_count;
    }

    public void setVote_count(Integer vote_count) {
        this.vote_count = vote_count;
    }
}
