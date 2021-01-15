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

            c.connect();

            pstmt = c.createPreparedStatement(sql);

            return pstmt.executeQuery();
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

    public static String[] select(String[] fields, String table, String where) throws SQLException {
        try {
            String fieldsString = "";

            for ( String field: fields ) {
                fieldsString += String.format(",%s", field);
            }

            String fieldsStringFiltered = fieldsString.substring(1);

            ResultSet responseQuery = createQuery("SELECT " + fieldsStringFiltered + " FROM " + table + String.format(" %s", where != null ? where : ""));

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

    public static void query(String query) throws SQLException {
        try {
            createQuery(query);
            endQuery();
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
    }
}
