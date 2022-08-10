package com.techelevator.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import com.techelevator.model.Movie;
import com.techelevator.model.Person;
import com.techelevator.model.UserNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.techelevator.model.User;

@Component
public class JdbcMovieDao implements MovieDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcMovieDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    ////////////ALGORITHM STARTS HERE/////////////////

    public List<Movie> getNewRecommendations(int userId, List<Movie> swipes){
        List<Movie> recommendations = new ArrayList<>();
        postSwipes(userId,swipes);

        HashMap preferences = new HashMap();



        return recommendations;
    }

    public void postSwipes(int userId, List<Movie> swipes){
        int[] movieId = new int[10];
        for(int i = 0; i<movieId.length; i++) {
            if(i<swipes.size()){
                movieId[i]=swipes.get(i).getId();
            }else{
                movieId[i] = 0;
            }
        }

        String postSwipes = "INSERT INTO history_swipes (user_id, movie_1_id, movie_2_id, movie_3_id, movie_4_id, movie_5_id, movie_6_id, movie_7_id, movie_8_id, movie_9_id, movie_10_id,) VALUES\n"+
                "(?,?,?,?,?,?,?,?,?,?,?);";

        try{
            jdbcTemplate.queryForRowSet(postSwipes, userId,movieId[0],movieId[1],movieId[2],movieId[3],movieId[4],movieId[5],movieId[6],movieId[7],movieId[8],movieId[9]);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<Movie> 


    ///////////ALGORITHM ENDS HERE/////////////

    ///////////ALGORITHM HELPERS////////////

    public List<Movie> getMovieByActor(String actor){

        List<Movie> movies = new ArrayList<>();

        String sql = "SELECT  movie_id, title, date_released, poster, name as actor FROM movie\n" +
                "JOIN person on director_id=person_id\n"+
                "JOIN movie_genre using (movie_id)\n" +
                "JOIN genre using (genre_id)\n" +
                "WHERE genre_name ILIKE ?;";

        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, "%" + actor + "%");
            while (rowSet.next()) {
                movies.add(mapRowToMovie(rowSet));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movies;
    }

public Movie getRandomMovie(int limit){
        String sql = "SELECT movie_id, title, date_released, poster, name as director FROM movie "+
                "JOIN person_id on director_id = person_id\n" +
                "ORDER BY rand() LIMIT ?;";

        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, limit);
            if (rowSet.next())
                return mapRowToMovie(rowSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Movie> getMovieByGenre(String genre) {

        List<Movie> movies = new ArrayList<>();

        String sql = "SELECT movie_id, title, date_released, poster, name as director FROM movie\n" +
                "JOIN person on director_id=person_id\n" +
                "JOIN movie_genre using (movie_id)\n" +
                "JOIN genre using (genre_id)\n" +
                "WHERE genre_name ILIKE ?;";

        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, "%" + genre + "%");
            while (rowSet.next()) {
                movies.add(mapRowToMovie(rowSet));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movies;
    }

    public List<Movie> getMovieByDirector(String director) {

        List<Movie> movies = new ArrayList<>();

        String sql = "SELECT movie_id, title, date_released, poster, name as director FROM movie\n" +
                "JOIN person on director_id=person_id\n"+
                "WHERE name ILIKE ?;";

        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, "%" + director + "%");
            while (rowSet.next()) {
                movies.add(mapRowToMovie(rowSet));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movies;
    }

    public List<Movie> searchByTitle(String iLike){
        List<Movie> movies = new ArrayList<>();

        String sql = "SELECT movie_id, title, date_released, poster, name as director FROM movie\n" +
                "JOIN person ON director_id = person_id\n"+
                "WHERE title ILIKE ?;";

        try{
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, "%"+iLike+"%");
            while(rowSet.next()){
                movies.add(mapRowToMovie(rowSet));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return movies;
    }

public Movie setMovieGenre(Movie movie){

        String sql = "select genre_name from movie\n" +
                "join movie_genre using (movie_id)\n" +
                "join genre using (genre_id)\n" +
                "where title = ?;";

        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, movie.getTitle());
            while (rowSet.next()) {
                mapRowToGenre(movie, rowSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movie;
    }


/////////////////////OBJECT HELPERS////////////////////

    private Movie mapRowToMovie(SqlRowSet rowSet) {
        Movie movie = new Movie();

        movie.setId(rowSet.getInt("movie_id"));
        movie.setTitle(rowSet.getString("title"));
        movie.setDatePremiered(rowSet.getDate("date_released"));
        movie.setDirector(rowSet.getString("director"));
        movie.setPoster(rowSet.getString("poster"));

        return movie;
    }

    private Movie mapRowToGenre(Movie movie, SqlRowSet rowSet) {
        movie.getGenres().add(rowSet.getString("genre_name"));

        return movie;
    }

    private Movie mapRowToActor(Movie movie, Person person, SqlRowSet rowSet) {
        person.setName(rowSet.getString("actor"));

        movie.getActors().add(person);

        return movie;
    }

    public boolean delete(int id) {
        try {
            jdbcTemplate.update("DELETE FROM user WHERE user_id = ?", id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
