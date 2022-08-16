package com.techelevator.dao;

import com.techelevator.model.Movie;

import java.util.List;

public interface MovieDao {

    public List<Movie> getRandomMovie(int limit);

    public List<Movie> getMovieByGenre(String genre);

    public List<Movie> getMovieByDirector(String director);

    public Movie setMovieGenre(Movie movie);

    public List<Movie> searchByTitle(String iLike);

    public List<Movie> getMovieByActor(String actor);

    public boolean delete(int id);

    public List<Movie> getNewRecommendations(int userId, List<Movie> swipes);

    public void postSwipes(int userId, List<Movie> swipes);

    public boolean addFavoriteMovies(int userId, List<Movie> favorited);

    public List<Movie> getFavoriteMovie (int id);

}
