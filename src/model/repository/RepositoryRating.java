package model.repository;

import database.Connect;
import model.entity.Movie;
import model.entity.Rating;
import model.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RepositoryRating {

    private Connect c = new Connect();

    public void createRating(Rating rating) {

        if(rating == null) return;

        String sql = "INSERT INTO rating(id,rating,id_user,id_movie) " +
                "VALUES(?,?,?,?);";

        c.connect();

        PreparedStatement p = c.createPreparedStatement(sql);

        try {
            p.setFloat(2,rating.getRating());
            p.setInt(3,rating.getUserId());
            p.setInt(4,rating.getMovieId());
            int didCreateRating = p.executeUpdate();
            System.out.println(didCreateRating);

        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if(p != null) {
                try {
                    p.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            c.disconnect();
        }
    }

    private ArrayList<Rating> readRatings(String attribute, String value) {

        String sql = "SELECT * FROM rating WHERE " + attribute + " = ?;";

        ResultSet result = null;

        c.connect();
        PreparedStatement p = null;
        ArrayList<Rating> ratings = new ArrayList<>();

        try {

            p = c.createPreparedStatement(sql);
            p.setString(1,value);
            result = p.executeQuery();

            while (result.next()) {
                Rating rating = new Rating(
                        result.getInt("id"),
                        result.getFloat("rating"),
                        result.getInt("id_user"),
                        result.getInt("id_movie")
                );

                ratings.add(rating);
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

        return ratings;
    }

    public ArrayList<Rating> readAllRatings() {

        String sql = "SELECT * FROM rating";

        ResultSet result = null;

        c.connect();
        PreparedStatement p = null;
        ArrayList<Rating> ratings = new ArrayList<>();

        try {

            p = c.createPreparedStatement(sql);
            result = p.executeQuery();

            while (result.next()) {
                Rating rating = new Rating(
                        result.getInt("id"),
                        result.getFloat("rating"),
                        result.getInt("id_user"),
                        result.getInt("id_movie")
                );

                ratings.add(rating);
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

        return ratings;
    }

    public ArrayList<Rating> readRatingsByMovie(Movie movie) {
        String sql = "SELECT r.id AS id, r.rating AS rating, r.id_user as id_user, r.id_movie as id_movie FROM rating r" +
                " INNER JOIN movie m ON m.id = r.id_movie" +
                " WHERE m.id = ?;";

        ResultSet result = null;

        c.connect();
        PreparedStatement p = null;
        ArrayList<Rating> ratings = new ArrayList<>();

        try {

            p = c.createPreparedStatement(sql);
            p.setInt(1, movie.getId());
            result = p.executeQuery();

            while (result.next()) {
                Rating rating = new Rating(
                        result.getInt("id"),
                        result.getFloat("rating"),
                        result.getInt("id_user"),
                        result.getInt("id_movie")
                );

                ratings.add(rating);
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

        return ratings;
    }

    public ArrayList<Rating> readUserRatingsByMovie(Movie movie, User user) {
        if(this.readRatingsByMovie(movie) == null) return null;

        ArrayList<Rating> ratings = this.readRatingsByMovie(movie);

        ArrayList<Rating> filteredRatings = new ArrayList<>();

        for ( Rating rating : ratings ) {
            if(rating.getId() == user.getId()) filteredRatings.add(rating);
        }

        return filteredRatings;
    }

    public Rating readRatingById(int rating_id){
        if( readRatings("id", Integer.toString(rating_id)).size() == 0 ) return null;
        return readRatings("id", Integer.toString(rating_id)).get(0);
    }

    public ArrayList<Rating> readRatingsByValue(String value) {
        return readRatings("rating", value);
    }

    public String readRaterUserName(int rating_id) {

        String sql = "SELECT u.name AS name FROM rating r" +
                " INNER JOIN user u ON u.id = r.id_user" +
                " WHERE r.id = ?;";

        ResultSet result = null;

        c.connect();
        PreparedStatement p = null;

        String userName = null;

        try {

            p = c.createPreparedStatement(sql);
            p.setInt(1,rating_id);
            result = p.executeQuery();

            while (result.next()) {
                userName = result.getString("name");
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

        return userName;
    }

    public float readAvgRatingByMovie(Movie movie) {

        String sql = "SELECT avg(r.rating) AS average FROM rating r" +
                " INNER JOIN movie m ON m.id = r.id_movie" +
                " WHERE m.id = ?;";

        ResultSet result = null;

        c.connect();
        PreparedStatement p = null;

        float average = 0;

        try {

            p = c.createPreparedStatement(sql);
            p.setInt(1,movie.getId());
            result = p.executeQuery();

            while (result.next()) {
                average = result.getFloat("average");
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

        return average;
    }

    public ArrayList<String> readAlreadyRatedEmails(Movie movie) {

        String sql = "SELECT u.email AS email FROM user u" +
                " INNER JOIN rating r ON r.id_user = u.id" +
                " INNER JOIN movie m ON r.id_movie = m.id" +
                " WHERE m.id = ?;";

        ResultSet result = null;

        c.connect();
        PreparedStatement p = null;

        ArrayList<String> ratedEmails = new ArrayList<>();

        try {

            p = c.createPreparedStatement(sql);
            p.setInt(1, movie.getId());
            result = p.executeQuery();

            while (result.next()) {
                String ratedEmail = result.getString("email");

                ratedEmails.add(ratedEmail);
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

        return ratedEmails;
    }

    private void updateRatingValue(String attribute, String attributeMatch, float newRating) {
        String sql = "UPDATE rating SET rating = ? WHERE "+ attribute +" = ?;";

        c.connect();
        PreparedStatement p = null;
        try{

            p = c.createPreparedStatement(sql);
            p.setFloat(1,newRating);
            p.setString(2,attribute);
            int didUpdateRating = p.executeUpdate();
            System.out.println(didUpdateRating);

        }catch (SQLException e){
            System.out.println(e.getMessage());

            e.printStackTrace();
        }finally {
            if (p != null) {
                try{
                    p.close();
                    c.disconnect();
                }catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                    ex.printStackTrace();
                }
            }
        }
    }

    public void updateRatingValueById(int rating_id, float newRating) { updateRatingValue("id", Integer.toString(rating_id), newRating); }

    private void deleteRating(String attribute,String value) {
        String sql = "DELETE FROM rating WHERE " + attribute + " = ?;";

        c.connect();
        PreparedStatement p = null;
        try{

            p = c.createPreparedStatement(sql);
            p.setString(1,value);
            int deletedRatings = p.executeUpdate();
            System.out.println(deletedRatings);

        }catch (SQLException e){
            System.out.println(e.getMessage());

            e.printStackTrace();
        }finally {
            if (p != null) {
                try{
                    p.close();
                    c.disconnect();
                }catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                    ex.printStackTrace();
                }
            }
        }
    }

    public void deleteRatingById(int rating_id) {
        deleteRating("id", Integer.toString(rating_id));
    }
}
