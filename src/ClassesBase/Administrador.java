package ClassesBase;

import java.time.LocalDate;

public class Administrador extends Usuario {

    public Administrador(int id, String nome, String email, String senha, LocalDate dataNacimento) {
        super(id, nome, email, senha, dataNacimento);
    }
}
