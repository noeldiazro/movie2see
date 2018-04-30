package es.montanus.movie2see.tests;

public class TestEmptyMovieListSortingByName extends TestMovieListSortingByName {

    public void testSorting() {
        sort();
        assertOrdered();
    }

    private void assertOrdered() {
        assertEquals(0, size());
    }
}
