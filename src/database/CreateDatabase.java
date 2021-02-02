package database;

import java.sql.SQLException;
import java.sql.Statement;

public class CreateDatabase {

    //CRIA UMA CONEXÃO COM O BANCO DE DADOS
    private final Connect c = new Connect();

    //________________________________________USER____________________________________________________________

    // CRIA A TABELA DE USUARIOS CASO ELA NÃO EXISTA
    public void createTableUser() {
        String query = "CREATE TABLE IF NOT EXISTS user(" +
                "id integer primary key AUTOINCREMENT," +
                "name varchar," +
                "email varchar," +
                "password varchar," +
                "birthDate varchar," +
                "admin bool" +
                ");";

        boolean connected = false;

        try {
            connected = c.connect();
            Statement statement = c.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (connected) c.disconnect();
        }
    }

    //________________________________________________________________________________________________________

    //________________________________________MOVIES____________________________________________________________

    //CRIA A TABELA DE FILMES CASO ELA NÃO EXISTA
    public void createTableMovie() {
        String query = "CREATE TABLE IF NOT EXISTS movie(" +
                "id integer primary key AUTOINCREMENT," +
                "name varchar," +
                "movieDirector varchar," +
                "movieGenre varchar," +
                "synopsis varchar ," +
                "minimumAge integer," +
                "images LONGBLOB" +
                ");";

        boolean connected = false;

        try {
            connected = c.connect();
            Statement statement = c.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (connected) c.disconnect();
        }
    }

    //__________________________________________________________________________________________________________

    //________________________________________RATING____________________________________________________________

    //CRIA A TABELA DE AVALIAÇÕES CASO ELA NÃO EXISTA
    public void createTableRating() {
        String query = "CREATE TABLE IF NOT EXISTS rating(" +
                "id integer primary key AUTOINCREMENT," +
                "rating float," +
                "id_user integer not null," +
                "id_movie integer not null," +
                "foreign key (id_user) references user(id)," +
                "foreign key (id_movie) references movie(id)" +
                ");";

        boolean connected = false;

        try {
            connected = c.connect();
            Statement statement = c.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (connected) c.disconnect();
        }
    }

    //________________________________________________________________________________________________________

    //________________________________________FAVORITE MOVIES____________________________________________________________

    //CRIA A TABELA DE FILMES FAVORITOS CASO ELA NÃO EXISTA
    public void createTableFavoriteMovies() {
        String query = "CREATE TABLE IF NOT EXISTS favoriteMovies (" +
                "    id       INTEGER PRIMARY KEY AUTOINCREMENT," +
                "    id_user  INTEGER REFERENCES user (id)," +
                "    id_movie INTEGER REFERENCES movie (id)" +
                ");";

        boolean connected = false;

        try {
            connected = c.connect();
            Statement statement = c.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (connected) c.disconnect();
        }
    }
}

//____________________________________________________________________________________________________________