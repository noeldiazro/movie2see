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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return name == movie.name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
