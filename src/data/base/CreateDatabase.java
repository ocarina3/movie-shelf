package data.base;

import model.entity.Movie;
import model.entity.User;
import utils.GenerateQuery;
import view.Main;


import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CreateDatabase {

    private final Connect c = new Connect();

    // Criação da tabela passando a query, cria a tabela apenas se ela não existe
    public void createTableUser() {
        String query = "CREATE TABLE IF NOT EXISTS user(" +
                "id integer primary key AUTOINCREMENT," +
                "name varchar," +
                "email varchar UNIQUE ON CONFLICT ABORT," +
                "password varchar," +
                "birthDate date," +
                "admin bool" +
                ");";
        boolean connected = false;

        try {
            connected = c.connect();
            Statement statement = c.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (connected) c.disconnect();
        }
    }

    public void createTableMovie() {
        String query = "CREATE TABLE IF NOT EXISTS movie(" +
                "id integer primary key AUTOINCREMENT," +
                "name varchar," +
                "score DOUBLE," +
                "movieDirector varchar," +
                "movieGenre varchar," +
                "synopsis varchar ," +
                "minimumAge integer" +
                ");";

        boolean connected = false;

        try {
            connected = c.connect();
            Statement statement = c.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (connected) c.disconnect();
        }
    }


    public void createTableRating() {
        String query = "CREATE TABLE IF NOT EXISTS rating(" +
                "id integer primary key AUTOINCREMENT," +
                "rating float," +
                "id_user integer," +
                "id_movie integer" +
                ");";

        boolean connected = false;

        try {
            connected = c.connect();
            Statement statement = c.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (connected) c.disconnect();
        }
    }






    //Criação de Usuario passando suas informações e o tipo de usuario
    public void createMovie(Movie movie)
    {

        if(movie == null) return;

        String sql = "INSERT INTO movie(id,name,score,movieDirector,movieGenre,synopsis,minimumAge) " +
                "VALUES(?,?,?,?,?,?,?);";

        c.connect();

        PreparedStatement p = c.createPreparedStatement(sql);

        try
        {
            p.setString(2,movie.getName());
            p.setDouble(3,movie.getScore());
            p.setString(4,movie.getMovieDirector());
            p.setString(5,movie.getMovieGenre().getDescription());
            p.setString(6, movie.getSynopsis());
            p.setInt(7,movie.getMinimumAge());
            int teste = p.executeUpdate();

        }catch (SQLException e)
        {
            System.out.println("Deu Erro");
        }
        finally {
            if(p != null)
            {
                try{
                    p.close();
                }catch (SQLException ex){
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,ex);
                    System.out.println("ERROOOO");
                }
            }
            c.disconnect();
        }
    }
}