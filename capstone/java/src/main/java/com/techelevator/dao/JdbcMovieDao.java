package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.techelevator.model.UserNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.techelevator.model.User;

@Component
public class JdbcMovieDao implements MovieDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcMovieDao (JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

public Movie getRandomMovie(){
        String sql = "SELECT title, date_released, poster_path, name as director FROM movie "+
                "JOIN person_id on director_id = person_id" +
                "ORDER BY rand() LIMIT 1;";

        try{
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
            return mapRowToMovie(rowSet.next());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
}

public List<Movie> getMovieByGenre(String genre){

        List<Movie> movies = new ArrayList<>();

        String sql = "SELECT title, date_released, poster_path, name as director FROM movie\n" +
                "JOIN movie_genre using (movie_id)\n" +
                "JOIN genre using (genre_id)\n" +
                "WHERE genre_name ILIKE ?;";

        try{
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, genre);
            while(rowSet.next()){
                movies.add(mapRowToMovie(rowSet));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return movies;
}

public List<Movie> getMovieByDirector(String director){

        List<Movie> movies = new ArrayList<>();

        String sql = "SELECT title, date_released, poster_path, name as director FROM movie/n" +
                "JOIN movie_genre using (movie_id)\n" +
                "JOIN genre using (genre_id)\n" +
                "WHERE director ILIKE ?;";

        try{
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, director);
            while(rowSet.next()){
                movies.add(mapRowToMovie(rowSet));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return movies;
}

public Movie setMovieGenre(Movie movie){

        String sql = "select genre_name from movie\n" +
                "join movie_genre using (movie_id)\n" +
                "join genre using (genre_id)\n" +
                "where title = ?;";

        try{
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, movie.getTitle());
            while(rowSet.next()){
                mapRowToGenre(movie, rowSet);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return movie;
}

public Movie mapRowToMovie(SqlRowSet rowSet){
        Movie movie = new Movie();
        
        movie.setTitle(rowSet.getString("title"));
        movie.setDatePremiered(rowSet.getDate("date_released"));
        movie.setDirector(rowSet.getString("director"));
        movie.setPosterPath(rowSet.getString("poster_path"));

        return movie;
}

public Movie mapRowToGenre(Movie movie, SqlRowSet rowSet){
        movie.getGenres.add(rowSet.getString("genre_name"));

        return movie;
}
}
