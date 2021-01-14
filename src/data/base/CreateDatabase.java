package data.base;

import model.entity.User;
import view.Main;


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
                "id integer primary key," +
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
                "id integer primary key," +
                "name varchar," +
                "score float," +
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
                "id integer primary key," +
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
    private void createUser(User user, boolean admin)
    {
        RemoveDatabase r = new RemoveDatabase();

        String sql = "INSERT INTO user(id,name,email,password,birthDate,admin) VALUES(?,?,?,?,?,?);";

        c.connect();

        PreparedStatement p = c.createPreparedStatement(sql);

        try
        {
            p.setString(2,user.getName());
            p.setString(3, user.getEmail());
            p.setString(4, user.getPassword());
            p.setDate(5,null);
            p.setBoolean(6,admin);
            int teste = p.executeUpdate();
        }catch (SQLException e)
        {
            try {
                p.setString(2, user.getName());
                p.setString(3,"NULL");
                p.setString(4, user.getPassword());
                p.setDate(5,null);
                p.setBoolean(6,admin);


                int teste = p.executeUpdate();
                r.deleteUserByEmail("NULL");

                System.out.println("email invalido");
            }catch (SQLException ex){

                System.out.println("ERRo misterioso");
            }

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

    public void createClient(User user) {
        createUser(user,false);
    }

    public void createAdmin(User user) {
        createUser(user,true);
    }




}