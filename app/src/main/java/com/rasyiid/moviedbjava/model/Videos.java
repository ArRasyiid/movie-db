package com.rasyiid.moviedbjava.model;

import java.util.List;

public class Videos {
    private Integer id;
    private List<Video> results;

    public Videos() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Video> getResults() {
        return results;
    }

    public void setResults(List<Video> results) {
        this.results = results;
    }
}
