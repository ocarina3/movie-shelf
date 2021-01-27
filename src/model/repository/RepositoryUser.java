package model.repository;

import data.base.Connect;
import model.entity.User;
import view.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RepositoryUser { //Criação de Usuario passando suas informações e o tipo de usuario

    private Connect c = new Connect();

    //_______________________________________________________________________________________________________________
    //CREATE

    private void createUser(User user, boolean admin)
    {


        String sql = "INSERT INTO user(id,name,email,password,birthDate,admin) VALUES(?,?,?,?,?,?);";
        c.connect();

        PreparedStatement p = c.createPreparedStatement(sql);
        System.out.println(user.getBirthDate());

        try
        {
            p.setString(2,user.getName());
            p.setString(3, user.getEmail());
            p.setString(4, user.getPassword());
            p.setString(5,user.getBirthDate().toString());
            p.setBoolean(6,admin);
            p.executeUpdate();
        }catch (SQLException e)
        {
          System.out.println(e);
        }
        finally {
            if(p != null)
            {
                try{
                    p.close();
                }catch (SQLException ex){
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,ex);
                    System.out.println("ERRO");
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

    //_______________________________________________________________________________________________________________
    //READ

    private ArrayList<User> readUsers(String attribute, String value) {
        String sql = "SELECT * FROM user WHERE " + attribute + " = ?;";

        ResultSet result = null;

        c.connect();
        PreparedStatement p = null;
        ArrayList<User> users = new ArrayList<>(1);

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
                String birthDate = result.getString("birthDate");
                user.setBirthDate(LocalDate.parse(birthDate));
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
        if(readUsers("id", value).size() != 0){
            return readUsers("id", value).get(0);}
        return null;
    }

    public User readUsersByEmail(String value) {
        if(readUsers("email", value).size() != 0){
        return readUsers("email", value).get(0);}
        return null;
    }

    public ArrayList<User> readUsersByName(String value) {
        if(readUsers("name", value).size() != 0){
            return readUsers("name", value);}
        return null;
    }

    //_______________________________________________________________________________________________________________
    //UPDATE

    public void updateUser(User user) {
        String sql = "UPDATE user SET name = ?, email = ?, password = ?, birthDate = ?  WHERE id = ?;";

        c.connect();
        PreparedStatement p = null;
        try{
            p = c.createPreparedStatement(sql);
            p.setString(1,user.getName());
            p.setString(2,user.getEmail());
            p.setString(3,user.getPassword());
            p.setString(4, user.getBirthDate().toString());
            p.setInt(5,user.getId());

            int i = p.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());

        }finally {
            if (p != null) {
                try{
                    p.close();
                    c.disconnect();
                }catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }

    //_______________________________________________________________________________________________________________
    //DELETE

    //Deleta Usuario pelo id ou pelo email
    private void deleteUser(String attribute,String value) {
        String sql = "DELETE FROM user WHERE " + attribute + " = ?";

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

    public void deleteUserByEmail(String value){
        deleteUser("email", value);
    }

    public void deleteUserById(String value){
        deleteUser("id", value);
    }
}
