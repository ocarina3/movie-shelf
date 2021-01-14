package utils;

import data.base.Connect;
import view.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GenerateQuery {

    private static Connect c = new Connect();
    private static PreparedStatement pstmt;

    private static ResultSet createQuery(String query) throws SQLException {
        try {
            String sql = query;

            System.out.println(sql);

            c.connect();

            pstmt = c.createPreparedStatement(sql);

            ResultSet result = pstmt.executeQuery();

            return result;
        } catch (SQLException err) {
            System.out.println(err.getMessage());
            return null;
        }
    }

    private static void endQuery() {
        if(pstmt != null) {
            try {
                pstmt.close();
                c.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.getMessage());
                c.disconnect();
            }
        }
    }

    public static String[] select(String[] fields) throws SQLException {
        try {
            String fieldsString = "";

            for ( String field: fields ) {
                fieldsString += String.format(",%s", field);
            }

            String fieldsStringFiltered = fieldsString.substring(1);

            ResultSet responseQuery = createQuery("SELECT " + fieldsStringFiltered + " FROM user");

            String[] response = new String[fields.length];

            int indexToInsert = 0;
            for ( String field : fields ) {
                if( responseQuery != null )
                    response[indexToInsert++] = responseQuery.getString(field);
            }

            return response;
        } catch (SQLException err) {
            System.out.println(err.getMessage());
            return new String[0];
        } finally {
            endQuery();
        }
    }
}
