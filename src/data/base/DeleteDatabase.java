package data.base;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteDatabase {

    private final Connect c = new Connect();




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

    public void deleteMovieByName(String value){deleteMovie("name",value);}


}
