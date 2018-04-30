package es.montanus.movie2see.tests;

import es.montanus.movie2see.Movie;

public class TestTwoElementMovieListSortingByName extends TestMovieListSortingByName {

    private Movie movieA;
    private Movie movieB;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        movieA = new Movie.Builder("A").build();
        movieB = new Movie.Builder("B").build();
    }

    public void testSorting_SortedList() {
        add(movieA);
        add(movieB);
        sort();
        assertOrdered();
    }

    public void testSorting_UnsortedList() {
        add(movieB);
        add(movieA);
        sort();
        assertOrdered();
    }

    private void assertOrdered() {
        assertEquals(2, size());
        assertEquals(movieA, get(0));
        assertEquals(movieB, get(1));
    }
}
