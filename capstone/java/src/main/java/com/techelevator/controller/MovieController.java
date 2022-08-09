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
@PreAuthorize("isAuthenticated()")
@RequestMapping("/movies")
public class MovieController {

    private JdbcMovieDao dao;

    public MovieController() {
        this.dao = new MovieDao();
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Movie> list(@RequestParam(defaultValue = "") String title_like) {
    List<Movie> movies = new ArrayList<>();

        if (!title_like.equals("")) {
             dao.searchByTitle(title_like);
        }

        return ;
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Movie> list(@RequestParam(defaultValue = "") String genre) {

        if (!genre.equals("")) {
            return dao.getMovieByGenre(genre);
        }
        return list()
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

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id) {
        dao.delete(id);
    }

}
