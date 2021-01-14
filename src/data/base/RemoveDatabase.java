package data.base;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RemoveDatabase {

    private final Connect c = new Connect();


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
