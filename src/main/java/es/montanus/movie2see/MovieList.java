package es.montanus.movie2see;

import java.util.ArrayList;
import java.util.Collection;

public class MovieList {
    private Collection<Movie> movies = new ArrayList<Movie>();

    public int size() {
        return movies.size();
    }

    public void add(Movie movieToAdd) {
        movies.add(movieToAdd);
    }

    public boolean contains(Movie movieToCheckFor) {
        return movies.contains(movieToCheckFor);
    }
}
