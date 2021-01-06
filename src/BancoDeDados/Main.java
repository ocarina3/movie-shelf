package BancoDeDados;

public class Main {
    public static void main(String[] args) {
        Conectar conectar = new Conectar();
        conectar.conectar();
        conectar.desconectar();

    }
}
