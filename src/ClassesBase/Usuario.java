package ClassesBase;

import java.time.LocalDate;
import java.util.Objects;

public class Usuario {

    //ATRIBUTOS
    private int id;
    private String nome;
    private String email;
    private String senha;
    private LocalDate dataNacimento;
    //_______________________________________________________________________________________________________________

    //CONSTRUCTOR
    public Usuario(int id, String nome, String email, String senha, LocalDate dataNacimento) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataNacimento = dataNacimento;
    }
    //_______________________________________________________________________________________________________________

    //GETTERS E SETTERS

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDataNacimento() {
        return dataNacimento;
    }

    public void setDataNacimento(LocalDate dataNacimento) {
        this.dataNacimento = dataNacimento;
    }
    //_______________________________________________________________________________________________________________

    //EQUALS
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id) && Objects.equals(nome, usuario.nome) &&
                Objects.equals(email, usuario.email) && Objects.equals(senha, usuario.senha) &&
                Objects.equals(dataNacimento, usuario.dataNacimento);
    }
    //_______________________________________________________________________________________________________________
}
