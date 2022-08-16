package com.techelevator.model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Movie {

    private int id;
    private String title;
    private String overview;
    private List<Genre> genres = new ArrayList<>();
    private String poster;
    private int directorId;
    private String director;
    private int runtime;
    private Date datePremiered;
    private List<Person> actors = new ArrayList<>();

    public Movie () {
    }

    public Movie(int id, String title, String overview, String poster, int directorId, int runtime, Date datePremiered) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.poster = poster;
        this.directorId = directorId;
        this.runtime = runtime;
        this.datePremiered = datePremiered;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public int getDirectorId() {
        return directorId;
    }

    public void setDirectorId(int directorId) {
        this.directorId = directorId;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public Date getDatePremiered() {
        return datePremiered;
    }

    public String getDirector() {
        return director;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setDatePremiered(Date datePremiered) {
        this.datePremiered = datePremiered;
    }

    public List<Person> getActors() {
        return actors;
    }
}
