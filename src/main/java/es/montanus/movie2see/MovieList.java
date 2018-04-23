package es.montanus.movie2see;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class MovieList {
    public static final String NULL_CATEGORY_MSG = "Category can't be null";
    private List<Movie> movies = new ArrayList<Movie>();

    public int size() {
        return movies.size();
    }

    public void add(Movie movieToAdd) throws DuplicateMovieException {
        checkDuplicate(movieToAdd);

        movies.add(movieToAdd);
    }

    public boolean contains(Movie movieToCheckFor) {
        return movies.contains(movieToCheckFor);
    }

    public Vector<Movie> toVector() {
        return new Vector<Movie>(movies);
    }

    public Movie get(int index) {
        return movies.get(index);
    }

    public void rename(Movie movie, String newName) throws DuplicateMovieException {
        final Movie potentialMovie = new Movie.Builder(newName).build();
        if (!movie.equals(potentialMovie))
            checkDuplicate(potentialMovie);

        movie.rename(newName);
    }

    private void checkDuplicate(Movie movie) throws DuplicateMovieException {
        if (contains(movie))
            throw new DuplicateMovieException(movie);
    }

    public MovieList filterByCategory(Category category) {
        if (category == null) throw new IllegalArgumentException(NULL_CATEGORY_MSG);

        final MovieList result = new MovieList();
        for (Movie movie: movies)
            if (movie.isOfCategory(category))
                result.add(movie);
        return result;
    }

    @Override
    public String toString() {
        return "[" + getStringFor(movies) + "]";
    }

    private String getStringFor(List<Movie> movies) {
        if (movies.isEmpty())
            return "";
        if (movies.size() == 1)
            return getStringFor(movies.get(0));
        return getStringFor(movies.get(0)) + ", " + getStringFor(movies.subList(1, movies.size()));
    }

    private String getStringFor(Movie movie) {
        return wrap("\"", movie.getName());
    }

    private String wrap(String wrapping, String text) {
        return wrapping + text + wrapping;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieList that = (MovieList) o;

        return this.movies.equals(that.movies);
    }

    @Override
    public int hashCode() {
        return movies.hashCode();
    }
}
