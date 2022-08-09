package com.techelevator.dao;

import com.techelevator.model.Movie;

import java.util.List;

public interface MovieDao {

    public Movie getRandomMovie(int limit);

    public List<Movie> getMovieByGenre(String genre);

    public List<Movie> getMovieByDirector(String director);

    public Movie setMovieGenre(Movie movie);

    public List<Movie> searchByTitle(String iLike);

    public List<Movie> getMovieByActor(String actor);
}
