package utils;

import data.base.Connect;
import view.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GenerateQuery {

    private static Connect c = new Connect();
    private static PreparedStatement pstmt;

    private static ResultSet createQueryWithResponse(String query) throws SQLException {
        try {
            String sql = query;

            c.connect();

            pstmt = c.createPreparedStatement(sql);

            return pstmt.executeQuery();
        } catch (SQLException err) {
            System.out.println(err.getMessage());
            System.out.println("entrou aqui");
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

    public static ArrayList<ArrayList<String>> select(String[] fields, String table, String where) {
        try {
            String fieldsString = "";

            for ( String field: fields ) {
                fieldsString += String.format(",%s", field);
            }

            String fieldsStringFiltered = fieldsString.substring(1);

            ResultSet responseQuery = createQueryWithResponse("SELECT " + fieldsStringFiltered + " FROM " + table + String.format(" %s", where != null ? where : ""));

            ArrayList<ArrayList<String>> response = new ArrayList<>();

            // Build the ArrayList with response data
            assert responseQuery != null;
            while ( responseQuery.next() ) {
                response.add(new ArrayList<>());

                for ( String field : fields ) {
                    response.get(responseQuery.getRow() - 1).add(responseQuery.getString(field));
                }
            }


            return response;
        } catch (SQLException err) {
            System.out.println(err.getMessage());
            return new ArrayList<>();
        } finally {
            endQuery();
        }
    }

    public static ResultSet selectWithRelationships(String query) {
        try {
            ResultSet result = createQueryWithResponse(query);
            endQuery();
            return result;
        } catch (SQLException err) {
            System.out.println(err.getMessage());
            return null;
        }
    }
}
