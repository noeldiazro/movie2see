package es.montanus.movie2see.tests;

import es.montanus.movie2see.Movie;
import es.montanus.movie2see.MovieList;
import es.montanus.movie2see.MovieNameComparator;
import junit.framework.TestCase;

import java.util.Comparator;

public abstract class TestMovieListSortingByName extends TestCase {

    private MovieList movieList;
    private Comparator<Movie> nameComparator;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        movieList = new MovieList();
        nameComparator = new MovieNameComparator();
    }

    protected void add(Movie movie) {
        movieList.add(movie);
    }

    protected void sort() {
        movieList.sortUsing(nameComparator);
    }

    protected int size() {
        return movieList.size();
    }

    protected Movie get(int index) {
        return movieList.get(index);
    }
}
