package com.eldeeb.movieapp.model;

import java.io.Serializable;
import java.security.PrivateKey;

public class MovieModel implements Serializable {
    private String title,backdrop_path,release_date,overview,poster_path;
    private float vote_average;

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public float getVote_average() {
        return vote_average;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }
}
