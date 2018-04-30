package es.montanus.movie2see.tests;

import es.montanus.movie2see.Movie;

public class TestOneElementMovieListSortingByName extends TestMovieListSortingByName {

    private Movie movieA;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        movieA = new Movie.Builder("A").build();
    }

    public void testSorting() {
        add(movieA);
        sort();
        assertOrdered();
    }

    private void assertOrdered() {
        assertEquals(1, size());
        assertEquals(movieA, get(0));
    }
}
