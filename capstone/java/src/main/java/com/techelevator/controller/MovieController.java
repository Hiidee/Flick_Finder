package com.techelevator.controller;


import com.techelevator.model.Movie;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/movies")
public class MovieController {

    private MovieDao dao;

    public MovieController() {
        this.dao = new MemoryMovieDao();
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Movie> list(@RequestParam(defaultValue = "") String title_like) {

        if (!title_like.equals("")) {
            return dao.searchByTitle(title_like);
        }

        return dao.list();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Movie get(@PathVariable int id) {
        return dao.get(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "", method = RequestMethod.POST)
    public Movie create(@Valid @RequestBody Movie movie) {
        return dao.create(movie);
    }

    @PreAuthorize("haRole('ADMIN')")
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public Movie update(@Valid @RequestBody Movie movie, @PathVariable int id) {
        return dao.update(movie, id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id) {
        dao.delete(id);
    }

}
