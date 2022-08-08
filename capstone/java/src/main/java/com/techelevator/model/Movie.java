package com.techelevator.model;

import java.sql.Time;
import java.util.Date;

public class Movie {

    private int id;
    private String title;
    private String overview;
    private String poster;
    private int directorId;
    private Time runtime;
    private Date datePremiered;

    public Movie () {
    }

    public Movie(int id, String title, String overview, String poster, int directorId, Time runtime, Date datePremiered) {
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

    public Time getRuntime() {
        return runtime;
    }

    public void setRuntime(Time runtime) {
        this.runtime = runtime;
    }

    public Date getDatePremiered() {
        return datePremiered;
    }

    public void setDatePremiered(Date datePremiered) {
        this.datePremiered = datePremiered;
    }
}
