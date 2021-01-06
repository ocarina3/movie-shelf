package BancoDeDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conectar {
    private Connection conexao;

    public boolean conectar(){
        try
        {
            String url = "jdbc:sqlite:BancoDeDados/BancoSQLite";

            this.conexao = DriverManager.getConnection(url);
        }catch(SQLException e) {

            System.out.println(e.getMessage());
            return false;
        }
        System.out.println("conectou");
        return true;
    }

    public boolean desconectar()
    {
        try
        {
            if(this.conexao.isClosed() == false)
            {
                this.conexao.close();
            }
        }catch(SQLException e) {

            System.out.println(e.getMessage());
            return false;
        }
        System.out.println("desconectou");
        return true;

    }
}
