package es.montanus.movie2see;

public class Movie {
    private final String name;

    public Movie(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
