package com.techelevator.dao;

import com.techelevator.model.Movie;
import com.techelevator.model.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcProfileDao implements ProfileDao{
    private final JdbcTemplate jdbcTemplate;

    public JdbcProfileDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean createProfileOnRegister(int userId){
        String checkQuery = "SELECT * FROM profile WHERE user_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(checkQuery, userId);
        if(rowSet.next()){
            return false;
        }

        String sql = "INSERT INTO profile (user_id,first_name,last_name,email)\n"+
                "VALUES (?,'','','');";
        try{
            jdbcTemplate.update(sql, userId);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Profile getUserProfile(int userId){
        Profile profile = new Profile();
        String sql = "SELECT * FROM profile WHERE user_id = ?;";

        try{
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql,userId);
            if(rowSet.next()){
                profile=mapRowToProfile(rowSet);
                profile.setFavoriteGenres(getGenreForProfile(userId));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return profile;
    }

    public boolean updateUserProfile(Profile profile){
        String sql = "UPDATE profile SET email = ?, first_name = ?, last_name = ? WHERE user_id = ?;";
        boolean successful;
        try{
            jdbcTemplate.update(sql, profile.getEmailAddress(), profile.getFirstName(), profile.getLastName(), profile.getUser_id());
            successful=true;
        }catch (Exception e){
            e.printStackTrace();
            successful=false;
        }

        String genreSql = "SELECT genre_id FROM genre WHERE genre_name = ?;";

        for(String genre: profile.getFavoriteGenres()) {
            try {
                SqlRowSet genreRowSet = jdbcTemplate.queryForRowSet(genreSql, genre);
                while (genreRowSet.next()) {
                    int genreId = genreRowSet.getInt("genre_id");
                    String insertGenre = "INSERT INTO favorite_genre (genre_id,user_id) values (?,?);";
                    jdbcTemplate.update(insertGenre, genreId,profile.getUser_id());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return successful;
    }

    public void addGenres(Profile profile){

    }
////////////HELPER METHODS//////////////


    private List<String> getGenreForProfile(int userId){
        List<String> genres = new ArrayList<>();

        String sql = "SELECT genre_name FROM genre\n"+
                "JOIN favorite_genre USING (genre_id)\n"+
                "WHERE user_id = ?;";

        try{
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId);
            while(rowSet.next()){
                genres.add(rowSet.getString("genre_name"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return genres;
    }

    private List<Movie> getMovieForProfile(int userId){
        MovieDao movieDao = new JdbcMovieDao(jdbcTemplate);
        List<Movie> movies = new ArrayList<>();

        movies = movieDao.getFavoriteMovie(userId);

        return movies;
    }

    private Profile mapRowToProfile(SqlRowSet rowSet){
        Profile profile= new Profile();

        profile.setFirstName(rowSet.getString("first_name"));
        profile.setLastName(rowSet.getString("last_name"));
        profile.setEmailAddress(rowSet.getString("email"));

        return profile;
    }
}
