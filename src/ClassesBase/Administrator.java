package ClassesBase;

import java.time.LocalDate;

public class Administrator extends User {

    public Administrator(int id, String nome, String email, String senha, LocalDate dataNacimento) {
        super(id, nome, email, senha, dataNacimento);
    }
}
