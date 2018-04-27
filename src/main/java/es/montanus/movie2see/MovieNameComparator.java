package es.montanus.movie2see;

import java.util.Comparator;

public class MovieNameComparator implements Comparator<Movie> {
    @Override
    public int compare(Movie movie1, Movie movie2) {
        return movie1.getName().compareTo(movie2.getName());
    }
}
