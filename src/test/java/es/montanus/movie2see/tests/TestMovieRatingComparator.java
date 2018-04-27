package es.montanus.movie2see.tests;

import es.montanus.movie2see.Movie;
import es.montanus.movie2see.MovieRatingComparator;
import es.montanus.movie2see.Rating;
import junit.framework.TestCase;

public class TestMovieRatingComparator extends TestCase {

    private MovieRatingComparator comparator;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        comparator = new MovieRatingComparator();
    }

    public void testCompare_BothMoviesHaveSameRating() {
        final int value = 3;
        Movie movie1 = getMovieWithRating("A", value);
        Movie movie2 = getMovieWithRating("B", value);
        assertEquals(0, comparator.compare(movie1, movie2));
    }

    public void testCompare_BothMoviesHaveDifferentRating() {
        Movie movie1 = getMovieWithRating("A", 1);
        Movie movie2 = getMovieWithRating("B", 2);
        assertTrue(comparator.compare(movie1, movie2) < 0);
        assertTrue(comparator.compare(movie2, movie1) > 0);
    }

    public void testCompare_OneMovieIsNotRated() {
        Movie movieWithoutRating = getMovieWithoutRating("A");
        Movie movieWithRating = getMovieWithRating("B", 1);
        assertTrue(comparator.compare(movieWithoutRating, movieWithRating) < 0);
        assertTrue(comparator.compare(movieWithRating, movieWithoutRating) > 0);
    }

    public void testCompare_NeitherMovieIsRated() {
        Movie movie1 = getMovieWithoutRating("A");
        Movie movie2 = getMovieWithoutRating("B");
        assertEquals(0, comparator.compare(movie1, movie2));
    }

    private Movie getMovieWithoutRating(String name) {
        return new Movie.Builder(name).build();
    }

    private Movie getMovieWithRating(String name, int value) {
        return new Movie.Builder(name).setRating(new Rating(value)).build();
    }
}
