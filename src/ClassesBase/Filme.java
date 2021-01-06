package ClassesBase;

import java.util.Objects;

public class Filme {

    //ATRIBUTOS
    private int id;
    private String nome;
    private float nota;
    private String diretor;
    private Genero genero;
    private String sinopse;
    private int idadeMinima;
    //_______________________________________________________________________________________________________________

    //CONSTRUCTOR
    public Filme(int id, String nome, float nota, String diretor, Genero genero, String sinopse, int idadeMinima) {
        this.id = id;
        this.nome = nome;
        this.nota = nota;
        this.diretor = diretor;
        this.genero = genero;
        this.sinopse = sinopse;
        this.idadeMinima = idadeMinima;
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

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public int getIdadeMinima() {
        return idadeMinima;
    }

    public void setIdadeMinima(int idadeMinima) {
        this.idadeMinima = idadeMinima;
    }
    //_______________________________________________________________________________________________________________

    //EQUALS
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Filme)) return false;
        Filme filme = (Filme) o;
        return id == filme.id && Float.compare(filme.nota, nota) == 0 && idadeMinima == filme.idadeMinima &&
                Objects.equals(nome, filme.nome) && Objects.equals(diretor, filme.diretor) &&
                genero == filme.genero && Objects.equals(sinopse, filme.sinopse);
    }
    //_______________________________________________________________________________________________________________
}
