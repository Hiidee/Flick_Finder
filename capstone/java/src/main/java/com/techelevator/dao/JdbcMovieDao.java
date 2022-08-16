package com.techelevator.dao;

import java.rmi.MarshalledObject;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import com.techelevator.model.Genre;
import com.techelevator.model.Movie;
import com.techelevator.model.Person;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcMovieDao implements MovieDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcMovieDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    ////////////ALGORITHM STARTS HERE/////////////////

    public List<Movie> getNewRecommendations(int userId, List<Movie> swipes) {
        List<Movie> recommendations = new ArrayList<>();
        //Delete current cache
        delete(userId);
        //Add new cache

        //postSwipes(userId, swipes);


        //Adds individual preferences to list format
        List<String> directors = new ArrayList<>();
        List<String> genres = new ArrayList<>();
        List<String> actors = new ArrayList<>();

        for (int i = 0; i < swipes.size(); i++) {
            Movie movie = swipes.get(i);
            for (int x = 0; x < movie.getActors().size(); x++) {
                actors.add(movie.getActors().get(x).getName());
            }
            for (int y = 0; y < movie.getGenres().size(); y++) {
                genres.add(movie.getGenres().get(y).getName());
            }
            directors.add(movie.getDirector());
        }

        //Map unique preferences to number of occurrences
        HashMap<String, Integer> directorOccurrences = new HashMap<>();
        HashMap<String, Integer> genreOccurrences = new HashMap<>();
        HashMap<String, Integer> actorOccurrences = new HashMap<>();

        for (String director : directors) {
            if (directorOccurrences.containsKey(director)) {
                directorOccurrences.put(director, directorOccurrences.get(director) + 1);
            } else {
                directorOccurrences.put(director, 1);
            }
        }

        for (String genre : genres) {
            if (genreOccurrences.containsKey(genre)) {
                genreOccurrences.put(genre, genreOccurrences.get(genre) + 1);
            } else {
                genreOccurrences.put(genre, 1);
            }
        }

        for (String actor : actors) {
            if (actorOccurrences.containsKey(actor)) {
                actorOccurrences.put(actor, actorOccurrences.get(actor) + 1);
            } else {
                actorOccurrences.put(actor, 1);
            }
        }

        //Maps used for matching movies and determining ratios of each
        double preferenceSize = directors.size() + genres.size() + actors.size()/10;

        for (Map.Entry<String, Integer> director : directorOccurrences.entrySet()) {
            List<Movie> preferenceReturn = new ArrayList<>();
            preferenceReturn = getMovieByDirector(director.getKey());
            double numberFromPreference = (director.getValue() / preferenceSize) * 75;
            if(preferenceReturn.size()>0) {
                for (int i = 0; i < numberFromPreference; i++) {
                    if (preferenceReturn.size() > 1) {
                        int randomNumInRange = ThreadLocalRandom.current().nextInt(0, preferenceReturn.size());
                        Movie movie = preferenceReturn.get(randomNumInRange);
                        boolean shouldAdd = true;
                        for(Movie recommended: recommendations){
                            if(movie.getTitle().equals(recommended.getTitle())){
                                shouldAdd = false;
                            }
                        }
                        for(Movie swipe: swipes) {
                            if (movie.getTitle().equals(swipe.getTitle())){
                                shouldAdd = false;
                            }
                        }
                        if (shouldAdd) {
                            recommendations.add(movie);
                        }
                        preferenceReturn.remove(randomNumInRange);
                    } else {
                        recommendations.add(preferenceReturn.get(0));
                    }
                }
            }
        }

        for (Map.Entry<String, Integer> genre : genreOccurrences.entrySet()) {
            List<Movie> preferenceReturn = new ArrayList<>();
            preferenceReturn = getMovieByDirector(genre.getKey());
            double numberFromPreference = (genre.getValue() / preferenceSize) * 75;
            if(preferenceReturn.size()>0) {
                for (int i = 0; i < numberFromPreference; i++) {
                    if (preferenceReturn.size() > 1) {
                        int randomNumInRange = ThreadLocalRandom.current().nextInt(0, preferenceReturn.size());
                        Movie movie = preferenceReturn.get(randomNumInRange);
                        boolean shouldAdd = true;
                        for(Movie recommended: recommendations) {
                            if (movie.getTitle().equals(recommended.getTitle())) {
                                shouldAdd = false;
                            }
                        }
                        for(Movie swipe: swipes) {
                            if (movie.getTitle().equals(swipe.getTitle())){
                                shouldAdd = false;
                            }
                        }
                        if (shouldAdd) {
                            recommendations.add(movie);
                        }
                        preferenceReturn.remove(randomNumInRange);
                    } else {
                        recommendations.add(preferenceReturn.get(0));
                    }
                }
            }
        }

        for (Map.Entry<String, Integer> actor : actorOccurrences.entrySet()) {
            List<Movie> preferenceReturn = new ArrayList<>();
            preferenceReturn = getMovieByDirector(actor.getKey());
            double numberFromPreference = (actor.getValue() / preferenceSize) * 75;
            if(preferenceReturn.size()>0) {
                for (int i = 0; i < numberFromPreference; i++) {
                    if (preferenceReturn.size() > 1) {
                        int randomNumInRange = ThreadLocalRandom.current().nextInt(0, preferenceReturn.size());
                        Movie movie = preferenceReturn.get(randomNumInRange);
                        boolean shouldAdd = true;
                        for(Movie recommended: recommendations) {
                            if (movie.getTitle().equals(recommended.getTitle())) {
                                shouldAdd = false;
                            }
                        }
                        for(Movie swipe: swipes) {
                            if (movie.getTitle().equals(swipe.getTitle())){
                                shouldAdd = false;
                            }
                        }
                        if (shouldAdd) {
                            recommendations.add(movie);
                        }
                        preferenceReturn.remove(randomNumInRange);
                    } else {
                        recommendations.add(preferenceReturn.get(0));
                    }
                }
            }
        }

        recommendations = getRandomToFillRemaining(recommendations);

        Collections.shuffle(recommendations,new Random());

        return recommendations;
    }

    public List<Movie> getRandomToFillRemaining(List<Movie> recommendations) {
        //Fill in recommendations with remaining random movies
        int remainingFrom100 = 100 - recommendations.size();
        List<Movie> fillerRandom = new ArrayList<>();

        fillerRandom = getRandomMovie(remainingFrom100);
        recommendations.addAll(fillerRandom);

        return recommendations;
    }


    public void postSwipes(int userId, List<Movie> swipes) {
        int[] movieId = new int[10];
        for (int i = 0; i < movieId.length; i++) {
            if (i < swipes.size()) {
                movieId[i] = swipes.get(i).getId();
            } else {
                movieId[i] = 0;
            }
        }

        String postSwipes = "INSERT INTO history_swipes (user_id, movie_1_id, movie_2_id, movie_3_id, movie_4_id, movie_5_id, movie_6_id, movie_7_id, movie_8_id, movie_9_id, movie_10_id,) VALUES\n" +
                "(?,?,?,?,?,?,?,?,?,?,?);";

        try {
            jdbcTemplate.queryForRowSet(postSwipes, userId, movieId[0], movieId[1], movieId[2], movieId[3], movieId[4], movieId[5], movieId[6], movieId[7], movieId[8], movieId[9]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /////////////ALGORITHM ENDS HERE//////////////

    ///////START FAVORITE ASSIGNMENTS TO DB///////

    public boolean addFavoriteMovies(int userId, List<Movie> favorited) {
        try {
            List<Integer> userFavorites = new ArrayList<>();
            for(Movie movie : favorited) {
                userFavorites.add(movie.getId());
            }
            for(int movieId : userFavorites) {
                String sql = "INSERT INTO movie_favorite (user_id, movie_id) VALUES(?, ?)";

                jdbcTemplate.update(sql, userId, movieId);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    ///////END FAVORITE ASSIGNMENTS TO DB/////////

    /////////////ALGORITHM HELPERS////////////////

    public List<Movie> getMovieByActor(String actor) {

        List<Movie> movies = new ArrayList<>();

        String sql = "SELECT overview, movie_id, title, release_date, poster_path FROM movie\n" +
                "JOIN movie_actor USING (movie_id)\n" +
                "JOIN person on actor_id=person_id\n" +
                "WHERE person_name ILIKE ?;";

        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, "%"+actor+"%");
            while (rowSet.next()) {
                Movie movie = mapRowToMovieForActor(rowSet);
                setMovieDirector(movie);
                setMovieActors(movie);
                setMovieGenre(movie);

                movies.add(movie);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movies;
    }

    public List<Movie> getRandomMovie(int limit) {
        List<Movie> movies = new ArrayList<>();

        String sql = "SELECT length_minutes, overview, movie_id, title, release_date, poster_path, person_name as director FROM movie " +
                "JOIN person on director_id = person_id\n" +
                "ORDER BY RANDOM() LIMIT ?;";

        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, limit);
            while (rowSet.next()) {
                Movie movie = mapRowToMovie(rowSet);
                setMovieActors(movie);
                setMovieGenre(movie);

                movies.add(movie);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movies;
    }

    public List<Movie> getMovieByGenre(String genre) {

        List<Movie> movies = new ArrayList<>();

        String sql = "SELECT length_minutes, overview, movie_id, title, release_date, poster_path, person_name as director FROM movie\n" +
                "JOIN person on director_id=person_id\n" +
                "JOIN movie_genre using (movie_id)\n" +
                "JOIN genre using (genre_id)\n" +
                "WHERE genre_name ILIKE ?;";

        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, "%" + genre + "%");
            while (rowSet.next()) {
                Movie movie = mapRowToMovie(rowSet);
                setMovieActors(movie);
                setMovieGenre(movie);

                movies.add(movie);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movies;
    }

    public List<Movie> getMovieByDirector(String director) {

        List<Movie> movies = new ArrayList<>();

        String sql = "SELECT length_minutes, overview, movie_id, title, release_date, poster_path, person_name as director FROM movie\n" +
                "JOIN person on director_id=person_id\n" +
                "WHERE person_name ILIKE ?;";

        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, "%" + director + "%");
            while (rowSet.next()) {
                Movie movie = mapRowToMovie(rowSet);
                setMovieActors(movie);
                setMovieGenre(movie);

                movies.add(movie);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movies;
    }

    public List<Movie> searchByTitle(String iLike) {
        List<Movie> movies = new ArrayList<>();

        String sql = "SELECT length_minutes, overview, movie_id, title, release_date, poster_path, person_name as director FROM movie\n" +
                "JOIN person ON director_id = person_id\n" +
                "WHERE title ILIKE ?;";

        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, "%" + iLike + "%");
            while (rowSet.next()) {
                Movie movie = mapRowToMovie(rowSet);
                setMovieActors(movie);
                setMovieGenre(movie);

                movies.add(movie);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movies;
    }

    public Movie setMovieGenre(Movie movie) {

        String sql = "select genre_name, genre_id from movie\n" +
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

    public Movie setMovieActors(Movie movie) {

        String sql = "SELECT person_name as actor FROM movie\n" +
                "JOIN movie_actor USING (movie_id)\n" +
                "JOIN person ON person_id=actor_id\n" +
                "WHERE title = ?;";

        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, movie.getTitle());
            while (rowSet.next()) {
                mapRowToActor(movie, rowSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movie;
    }

    public Movie setMovieDirector(Movie movie){

        String sql = "SELECT person_name as director FROM movie\n"+
                "JOIN person ON person_id = director_id\n" +
                "WHERE title = ?;";

        try{
            SqlRowSet rowSet=jdbcTemplate.queryForRowSet(sql, movie.getTitle());
            if(rowSet.next()){
                mapRowToDirector(movie,rowSet);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return movie;
    }

    public boolean delete(int id) {
        try {
            jdbcTemplate.update("DELETE FROM history_swipes WHERE user_id = ?", id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


/////////////////////OBJECT HELPERS////////////////////

    private Movie mapRowToMovie(SqlRowSet rowSet) {
        Movie movie = new Movie();

        movie.setId(rowSet.getInt("movie_id"));
        movie.setTitle(rowSet.getString("title"));
        movie.setDatePremiered(rowSet.getDate("release_date"));
        movie.setDirector(rowSet.getString("director"));
        movie.setPoster(rowSet.getString("poster_path"));
        movie.setOverview(rowSet.getString("overview"));
        movie.setRuntime(rowSet.getInt("length_minutes"));

        return movie;
    }

    private Movie mapRowToMovieForActor(SqlRowSet rowSet) {
        Movie movie = new Movie();

        movie.setId(rowSet.getInt("movie_id"));
        movie.setTitle(rowSet.getString("title"));
        movie.setDatePremiered(rowSet.getDate("release_date"));
        movie.setPoster(rowSet.getString("poster_path"));

        return movie;
    }

    private Movie mapRowToGenre(Movie movie, SqlRowSet rowSet) {
        Genre genre = new Genre();
        genre.setId(rowSet.getInt("genre_id"));
        genre.setName(rowSet.getString("genre_name"));
        movie.getGenres().add(genre);

        return movie;
    }

    private Movie mapRowToActor(Movie movie, SqlRowSet rowSet) {
        Person person = new Person();

        person.setName(rowSet.getString("actor"));
        movie.getActors().add(person);

        return movie;
    }

    private Movie mapRowToDirector(Movie movie, SqlRowSet rowSet){
        movie.setDirector(rowSet.getString("director"));

        return movie;
    }
}
