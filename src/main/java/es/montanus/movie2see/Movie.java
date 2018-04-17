package es.montanus.movie2see;

public class Movie {
    public static final String NULL_NAME_MSG = "Movie name can't be null";
    public static final String EMPTY_NAME_MSG = "Movie name can't be empty";
    private String name;

    public Movie(String name) {
        setName(name);
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

    public void rename(String newName) {
        setName(newName);
    }

    private void setName(String name) {
        checkNull(name);
        checkEmpty(name);
        this.name = name;
    }

    private void checkNull(String name) throws IllegalArgumentException {
        if (name == null)
            throw new IllegalArgumentException(NULL_NAME_MSG);
    }

    private void checkEmpty(String name) {
        if (name.isEmpty())
            throw new IllegalArgumentException(EMPTY_NAME_MSG);
    }
}
