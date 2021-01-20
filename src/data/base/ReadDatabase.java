package data.base;

import model.entity.Genre;
import model.entity.Movie;
import model.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReadDatabase {

    private final Connect c = new Connect();

    private ArrayList<User> readUsers(String attribute, String value) {
        String sql = "SELECT * FROM user WHERE " + attribute + " = ?;";

        ResultSet result = null;

        c.connect();
        PreparedStatement p = null;
        ArrayList<User> users = new ArrayList<>();

        try {

            p = c.createPreparedStatement(sql);
            p.setString(1,value);
            result = p.executeQuery();

            while (result.next()) {
                User user = new User();
                user.setId(result.getInt("id"));
                user.setName(result.getString("name"));
                user.setEmail(result.getString("email"));
                user.setPassword(result.getString("password"));
                user.setBirthDate(result.getDate(1).toLocalDate());
                users.add(user);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            if (p != null) {
                try{
                    p.close();
                    c.disconnect();
                }catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return users;
    }

    public User readUsersById(String value){
        return readUsers("id", value).get(0);
    }

    public User readUsersByEmail(String value) {
        return readUsers("email", value).get(0);
    }

    public ArrayList<User> readUsersByName(String value) {
        return readUsers("name", value);
    }

    private ArrayList<Movie> readMovies(String attribute, String value) {
        String sql = "SELECT * FROM movie WHERE " + attribute + " = ?;";

        ResultSet result = null;

        c.connect();
        PreparedStatement p = null;
        ArrayList<Movie> movies = new ArrayList<>();

        try {

            p = c.createPreparedStatement(sql);
            p.setString(1,value);
            result = p.executeQuery();

            while (result.next()) {
                Movie movie = new Movie();
                movie.setId(result.getInt("id"));
                movie.setName(result.getString("name"));
                movie.setScore(result.getDouble("score"));
                movie.setMovieDirector(result.getString("movieDirector"));
                // movie.setMovieGenre(Genre.valueOf(result.getString("movieGenre")));
                movie.setSynopsis(result.getString("synopsis"));
                movie.setMinimumAge(result.getInt("minimumAge"));
                movies.add(movie);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            if (p != null) {
                try{
                    p.close();
                    c.disconnect();
                }catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return movies;
    }

    public Movie readMoviesById(String value){
        return readMovies("id", value).get(0);
    }

    public ArrayList<Movie> readMoviesByName(String value) {
        return readMovies("name", value);
    }

}
