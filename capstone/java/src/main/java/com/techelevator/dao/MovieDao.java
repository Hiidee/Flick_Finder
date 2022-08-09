package com.techelevator.dao;

import com.techelevator.model.Movie;

import java.util.List;

public interface MovieDao {

    public Movie getRandomMovie();

    public List<Movie> getMovieByGenre(String genre);

    public List<Movie> getMovieByDirector(String director);

    public Movie setMovieGenre(Movie movie);
}
