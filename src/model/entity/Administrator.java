package model.entity;

import java.time.LocalDate;

public class Administrator extends User {

    public Administrator(String name, String email, String password, LocalDate birthDate) {
        super(name, email, password, birthDate);
    }
}
