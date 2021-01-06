package data.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {

    private Connection connection;

    public boolean connect(){
        try {
            String url = "jdbc:sqlite:data.base/alphadatabase";

            this.connection = DriverManager.getConnection(url);
        } catch(SQLException e) {

            System.out.println(e.getMessage());
            return false;
        }
        System.out.println("Connected");
        return true;
    }

    public boolean disconnect(){
        try {
            if(!this.connection.isClosed()) {
                this.connection.close();
            }
        } catch(SQLException e) {

            System.out.println(e.getMessage());
            return false;
        }
        System.out.println("Disconnected");
        return true;
    }
}
