package es.montanus.movie2see.tests;

import es.montanus.movie2see.MovieList;
import junit.framework.TestCase;

public abstract class TestMovieList extends TestCase {

    protected MovieList movieList;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        movieList = getPopulatedMovieList();
    }

    protected MovieList getPopulatedMovieList() {
        MovieList list = getEmptyMovieList();
        populate(list);
        return list;
    }

    protected MovieList getEmptyMovieList() {
        return new MovieList();
    }

    protected abstract void populate(MovieList list);
}
