package model.repository;

import data.base.Connect;
import model.entity.Genre;
import model.entity.Movie;
import view.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RepositoryMovie {

    private Connect c = new Connect();

    //Criação de Usuario passando suas informações e o tipo de usuario
    public void createMovie(Movie movie) {

        if(movie == null) return;

        String sql = "INSERT INTO movie(id,name,movieDirector,movieGenre,synopsis,minimumAge) " +
                "VALUES(?,?,?,?,?,?);";

        c.connect();

        PreparedStatement p = c.createPreparedStatement(sql);

        try {
            p.setString(2,movie.getName());
            p.setString(3,movie.getMovieDirector());
            p.setString(4,movie.getMovieGenre().toString());
            p.setString(5, movie.getSynopsis());
            p.setInt(6,movie.getMinimumAge());
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
                    System.out.println("ERROOOO");
                }
            }
            c.disconnect();
        }
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
                movie.setMovieDirector(result.getString("movieDirector"));
                movie.setMovieGenre(Genre.valueOf(result.getString("movieGenre")));
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

    public void updateMovie(Movie movie) {
        String sql = "UPDATE movie SET name = ?, movieDirector = ?, movieGenre = ?, synopsis = ?, minimumAge = ?" +
                "WHERE id = ?";

        c.connect();

        PreparedStatement p = null;

        try {
            p = c.createPreparedStatement(sql);
            p.setString(1, movie.getName());
            p.setString(2, movie.getMovieDirector());
            p.setString(3, movie.getMovieGenre().toString());
            p.setString(4, movie.getSynopsis());
            p.setInt(5, movie.getMinimumAge());
            p.setInt(6, movie.getId());
            p.executeUpdate();
        } catch (SQLException e) {
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

    private void deleteMovie(String attribute,String value) {
        String sql = "DELETE FROM movie WHERE " + attribute + " = ?";

        c.connect();
        PreparedStatement p = null;
        try{

            p = c.createPreparedStatement(sql);
            p.setString(1,value);
            int deletedUsers = p.executeUpdate();
            System.out.println(deletedUsers);

        }catch (SQLException e){

            e.printStackTrace();
        }finally {
            if (p != null) {
                try{
                    p.close();
                    c.disconnect();
                }catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void deleteMovieByName(String value) {
        deleteMovie("name",value);
    }

}
