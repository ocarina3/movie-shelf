package model;

import java.time.LocalDate;

public class Administrator extends User {

    public Administrator(int id, String name, String email, String password, LocalDate birthDate) {
        super(id, name, email, password, birthDate);
    }
}
