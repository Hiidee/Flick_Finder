package com.techelevator.controller;


import com.techelevator.dao.JdbcMovieDao;
import com.techelevator.dao.MovieDao;
import com.techelevator.model.Movie;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
//@PreAuthorize("isAuthenticated()")
@RequestMapping("/movies")
public class MovieController {

    private MovieDao dao;

    public MovieController(MovieDao movieDao) {
        this.dao = movieDao;
    }

    @RequestMapping(path = "/actor/{Actor}", method = RequestMethod.GET)
    public List<Movie> listByActor(@PathVariable String Actor) {
        List<Movie> movies = new ArrayList<>();

        if (!Actor.equals("")) {
            movies = dao.getMovieByActor(Actor);
        }

        return movies;
    }

    @RequestMapping(path = "/title/{title_like}", method = RequestMethod.GET)
    public List<Movie> getByTitle(@PathVariable String title_like) {
    List<Movie> movies = new ArrayList<>();

        if (!title_like.equals("")) {
             movies = dao.searchByTitle(title_like);
        }

        return movies;
    }

    @RequestMapping(path = "/genre/{genre}", method = RequestMethod.GET)
    public List<Movie> listByGenre(@PathVariable String genre) {
    List<Movie> movies = new ArrayList<>();
        if (!genre.equals("")) {
            return dao.getMovieByGenre(genre);
        }
        return movies;
    }

    @RequestMapping(path = "/director/{director}", method = RequestMethod.GET)
    public List<Movie> ListByDirector(@PathVariable String director) {
        List<Movie> movies = new ArrayList<>();
        if (!director.equals("")) {
            return dao.getMovieByDirector(director);
        }
        return movies;
    }

    @RequestMapping(path = "/random", method = RequestMethod.GET)
    public List<Movie> ListRandomMovies() {
        List<Movie> movies = dao.getRandomMovie(100);
        return movies;
    }


    @RequestMapping(path = "/recommendations/{userId}/", method = RequestMethod.POST)
    public List<Movie> ListRecommendedMovies(@RequestBody List<Movie> swipes,@PathVariable int userId) {
        List<Movie> movies = dao.getNewRecommendations(userId, swipes);
        return movies;
    }

//    @RequestMapping(path = "/favorites/save/{userId}/", method = RequestMethod.POST)
//    public boolean addFavoriteMovies(@RequestBody List<Movie> favorited, @PathVariable int userId){
//        return false;//NOT IMPLEMENTED
//    }

    @RequestMapping(path = "/favorites/save/{userId}/", method = RequestMethod.POST)
    public boolean addFavoriteMovies(@RequestBody List<Movie> favorited, @PathVariable int userId){
        return dao.addFavoriteMovies(userId, favorited);
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public boolean addMovieToDatabase(@RequestBody Movie movie) {
        return dao.addMovieToDatabase(movie);
    }

//    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
//    public Movie get(@PathVariable int id) {
//        return dao.get(id);
//    }
//
//    @PreAuthorize("hasRole('ADMIN')")
//    @ResponseStatus(HttpStatus.CREATED)
//    @RequestMapping(path = "", method = RequestMethod.POST)
//    public Movie create(@Valid @RequestBody Movie movie) {
//        return dao.create(movie);
//    }

//    @PreAuthorize("haRole('ADMIN')")
//    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
//    public Movie update(@Valid @RequestBody Movie movie, @PathVariable int id) {
//        return dao.update(movie, id);
//    }

//    @PreAuthorize("hasRole('ADMIN')")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
//    public void delete(@PathVariable int id) {
//        dao.delete(id);
//    }

}
