package model.repository;

import data.base.Connect;
import model.entity.Movie;
import model.entity.Rating;
import model.entity.User;
import view.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RepositoryRating {

    private Connect c = new Connect();

    public void createRating(Rating rating, User raterUser, Movie ratedMovie) {

        if(rating == null) return;

        String sql = "INSERT INTO rating(id,rating,id_user,id_movie) " +
                "VALUES(?,?,?,?);";

        c.connect();

        PreparedStatement p = c.createPreparedStatement(sql);

        try {
            p.setFloat(2,rating.getRating());
            p.setInt(3,raterUser.getId());
            p.setInt(4,ratedMovie.getId());
            int teste = p.executeUpdate();

        }catch (SQLException e) {
            System.out.println("Deu Erro");
        }
        finally {
            if(p != null) {
                try {
                    p.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,ex);
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

    public Rating readRatingById(String value){
        return readRatings("id", value).get(0);
    }

    public ArrayList<Rating> readRatingsByValue(String value) {
        return readRatings("ratings", value);
    }

    public String readRaterUserName(String rating_id) {

        String sql = "SELECT u.name AS name FROM rating AS r WHERE `r.id` = ?;" +
                "JOIN user AS u on u.id = r.id_user ";

        ResultSet result = null;

        c.connect();
        PreparedStatement p = null;

        String userName = null;

        try {

            p = c.createPreparedStatement(sql);
            p.setString(1,rating_id);
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

    public String readRatedMovieName(String rating_id) {

        String sql = "SELECT m.name AS name FROM rating AS r WHERE `r.id` = ?;" +
                "JOIN movie AS m on m.id = r.id_movie ";

        ResultSet result = null;

        c.connect();
        PreparedStatement p = null;

        String movieName = null;

        try {

            p = c.createPreparedStatement(sql);
            p.setString(1,rating_id);
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

    private void updateRatingValue(String attribute, String attributeMatch, float newRating) {
        String sql = "UPDATE rating SET rating = ? WHERE "+ attribute +" = ?";

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

    public void updateRatingValueById(String rating_id, float newRating) { updateRatingValue("id", rating_id, newRating); }

    private void deleteRating(String attribute,String value) {
        String sql = "DELETE FROM rating WHERE " + attribute + " = ?";

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

    public void deleteRatingById(String value) {
        deleteRating("id",value);
    }
}
