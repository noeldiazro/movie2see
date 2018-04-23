package es.montanus.movie2see.tests;

import es.montanus.movie2see.Category;
import es.montanus.movie2see.DuplicateMovieException;
import es.montanus.movie2see.Movie;
import es.montanus.movie2see.MovieList;
import junit.framework.TestCase;

public class TestMovieListWithTwoMovies extends TestCase {
    private MovieList movieList;
    private Movie starWars;
    private Movie starTrek;

    public void setUp() throws Exception {
        super.setUp();
        movieList = new MovieList();
        starWars = new Movie.Builder("Star Wars").setCategory(Category.SCIFI).build();
        starTrek = new Movie.Builder("Star Trek").setCategory(Category.SCIFI).build();
        movieList.add(starWars);
        movieList.add(starTrek);
    }

    public void testSizeShouldBeTwo() {
        assertEquals(2, movieList.size());
    }

    public void testShouldContainAddedMovies() {
        assertTrue(movieList.contains(starWars));
        assertTrue(movieList.contains(starTrek));
    }

    public void testShouldNotContainMovieThatWasNotAdded() {
        Movie stargate = new Movie.Builder("Stargate").build();
        assertFalse(movieList.contains(stargate));
    }

    public void testGetMovie() {
        assertEquals(starWars, movieList.get(0));
        assertEquals(starTrek, movieList.get(1));
    }

    public void testRenaming() {
        final String newName = "StarTrek I";
        movieList.rename(starTrek, newName);
        assertEquals(newName, starTrek.getName());
    }

    public void testRenamingDuplicate() {
        try {
            movieList.rename(starTrek, "Star Wars");
            fail();
        }
        catch (DuplicateMovieException expected) {
            assertEquals("Star Trek", starTrek.getName());
            assertEquals(
                    String.format(DuplicateMovieException.MOVIE_PRESENT_MSG, "Star Wars"),
                    expected.getMessage()
            );
        }

    }

    public void testFilterByCategory_() {
        MovieList expected = new MovieList();
        expected.add(new Movie.Builder("Star Wars").setCategory(Category.SCIFI).build());
        expected.add(new Movie.Builder("Star Trek").setCategory(Category.SCIFI).build());
        assertEquals(expected, movieList.filterByCategory(Category.SCIFI));
    }

    public void testToString() {
        assertEquals("[\"Star Wars\", \"Star Trek\"]", movieList.toString());
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestMovieListWithTwoMovies.class);
    }
}
