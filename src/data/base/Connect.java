package data.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {

    private Connection connection;

    // CONSTRUTOR
    public Connection getConnection() {
        return connection;
    }

    // MÉTODO PARA CONEXÃO DO BANCO
    public boolean connect(){
        try {
            String url = "jdbc:sqlite:data.base/alphadatabase";

            this.connection = DriverManager.getConnection(url);
        } catch(SQLException e) {

            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    // MÉTODO PARA DESCONEXÃO COM BANCO
    public boolean disconnect(){
        try {
            if(!this.connection.isClosed()) {
                this.connection.close();
            }
        } catch(SQLException e) {

            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    // MÉTODO PARA CRIAÇÃO DE UM STATEMENT
    public Statement createStatement() {
        try {
            return this.connection.createStatement();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
