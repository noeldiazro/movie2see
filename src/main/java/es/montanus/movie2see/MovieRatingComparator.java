package es.montanus.movie2see;

import java.util.Comparator;

/**
 * Note: this comparator imposes orderings that are inconsistent with equals.
 */
public class MovieRatingComparator implements Comparator<Movie> {
    @Override
    public int compare(Movie movie1, Movie movie2) {
        if (!movie1.isRated() && !movie2.isRated()) return 0;
        if (!movie1.isRated()) return -1;
        if (!movie2.isRated()) return 1;
        return movie1.getRating().compareTo(movie2.getRating());
    }
}
