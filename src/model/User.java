package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class User {

    //ATRIBUTOS
    private int id;
    private String name;
    private String email;
    private String password;
    private LocalDate birthDate;
    private ArrayList<Movie> favoriteMovies;
    //_______________________________________________________________________________________________________________

    //CONSTRUCTOR
    public User(int id, String name, String email, String password, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        favoriteMovies = new ArrayList<>();
    }
    //_______________________________________________________________________________________________________________

    //GETTERS E SETTERS

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    //_______________________________________________________________________________________________________________

    //EQUALS
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) &&
                Objects.equals(email, user.email) && Objects.equals(password, user.password) &&
                Objects.equals(birthDate, user.birthDate);
    }
    //_______________________________________________________________________________________________________________
}
