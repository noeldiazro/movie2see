package es.montanus.movie2see;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class MovieList {
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
        final Movie potentialMovie = new Movie(newName);
        if (!movie.equals(potentialMovie))
            checkDuplicate(potentialMovie);

        movie.rename(newName);
    }

    private void checkDuplicate(Movie movie) throws DuplicateMovieException {
        if (contains(movie))
            throw new DuplicateMovieException(movie);
    }
}
