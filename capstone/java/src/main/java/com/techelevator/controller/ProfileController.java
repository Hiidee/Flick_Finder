package com.techelevator.controller;

import javax.validation.Valid;

import com.techelevator.dao.MovieDao;
import com.techelevator.dao.ProfileDao;
import com.techelevator.model.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.techelevator.dao.UserDao;
import com.techelevator.security.jwt.JWTFilter;
import com.techelevator.security.jwt.TokenProvider;

@RestController
@PreAuthorize("isAuthenticated()")
@CrossOrigin
@RequestMapping("/profile")
public class ProfileController {

    private MovieDao movieDao;
    private ProfileDao profileDao;

    public ProfileController(MovieDao movieDao, ProfileDao profileDao){
        this.movieDao = movieDao;
        this.profileDao = profileDao;
    }

    @RequestMapping(path = "/create/{id}", method = RequestMethod.GET)
    public boolean createProfile(@PathVariable int id){
        return profileDao.createProfileOnRegister(id);
    }

    @RequestMapping(path = "/get/{id}",method = RequestMethod.GET)
    public Profile getProfile(@PathVariable int id){
        return profileDao.getUserProfile(id);
    }

    @RequestMapping(path = "/update/", method = RequestMethod.POST)
    public boolean updateProfile(@RequestBody Profile profile){
        return profileDao.updateUserProfile(profile);
    }


}

