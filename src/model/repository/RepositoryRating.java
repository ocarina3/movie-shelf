package model.repository;

import data.base.Connect;
import model.entity.Rating;
import view.principal.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public String readRatedMovieName(int rating_id) {

        String sql = "SELECT m.name AS name FROM rating r" +
                " INNER JOIN movie m ON m.id = r.id_movie" +
                " WHERE r.id = ?;";

        ResultSet result = null;

        c.connect();
        PreparedStatement p = null;

        String movieName = null;

        try {

            p = c.createPreparedStatement(sql);
            p.setString(1,Integer.toString(rating_id));
            result = p.executeQuery();

            while (result.next()) {
                movieName = result.getString("name");
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

        return movieName;
    }

    public ArrayList<String> readAlreadyRatedEmails(int movie_id) {

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
            p.setString(1,Integer.toString(movie_id));
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
