package model;

public enum Genre {

    ACTION_ADVENTURE("Ação e Aventura"),
    COMEDY("Comédia"),
    DRAMA("Drama"),
    FANTASY("Fantasia"),
    SCIENCE_FICTION("Ficção Científica"),
    HORROR("Terror");

    private final String description;

    Genre(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
