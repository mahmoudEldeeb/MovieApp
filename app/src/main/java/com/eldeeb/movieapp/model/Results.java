package com.eldeeb.movieapp.model;

import java.util.List;

public class Results {
    private List<MovieModel>results;
    private int total_pages,page,total_results;

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<MovieModel> getResults() {
        return results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setResults(List<MovieModel> results) {
        this.results = results;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }
}
