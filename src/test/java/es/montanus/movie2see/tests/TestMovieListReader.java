package es.montanus.movie2see.tests;

import es.montanus.movie2see.*;
import junit.framework.TestCase;

import java.io.IOException;
import java.io.StringReader;

public class TestMovieListReader extends TestCase {

    private MovieListReader movieListReader;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        movieListReader = new MovieListReader();
    }

    public void testReadFromEmptyStream() throws IOException {
        MovieList movieList = movieListReader.readFrom(new StringReader(""));
        assertEquals(getEmptyMovieList(), movieList);
    }

    private MovieList getEmptyMovieList() {
        return new MovieList();
    }

    public void testReadOneBasicMovie() throws IOException {
        MovieList movieList = movieListReader.readFrom(new StringReader("Star Wars|Science Fiction|4\n"));

        assertEquals(1, movieList.size());
        assertRatedMovie(movieList.get(0), "Star Wars", Category.SCIFI, new Rating(4));
    }

    public void testReadOneUncategorizedMovie() throws IOException {
        MovieList movieList = movieListReader.readFrom(new StringReader("Star Trek|Uncategorized|3\n"));

        assertEquals(1, movieList.size());
        assertRatedMovie(movieList.get(0), "Star Trek", Category.UNCATEGORIZED, new Rating(3));
    }

    private void assertRatedMovie(Movie movie, String name, Category category, Rating rating) {
        assertEquals(name, movie.getName());
        assertEquals(category, movie.getCategory());
        assertTrue(movie.isRated());
        assertEquals(rating, movie.getRating());
    }

    public void testReadOneUnratedMovie() throws IOException {
        MovieList movieList = movieListReader.readFrom(new StringReader("The Fellowship of The Ring|Fantasy|-1\n"));

        assertEquals(1, movieList.size());
        assertUnratedMovie(movieList.get(0), "The Fellowship of The Ring", Category.FANTASY);
    }

    private void assertUnratedMovie(Movie movie, String name, Category category) {
        assertEquals(name, movie.getName());
        assertEquals(category, movie.getCategory());
        assertFalse(movie.isRated());
    }

    public void testReadMultipleMovies() throws IOException {
        String input = "Star Wars|Science Fiction|4\n" +
                "Star Trek|Uncategorized|3\n" +
                "The Fellowship of The Ring|Fantasy|-1\n";

        MovieList movieList = movieListReader.readFrom(new StringReader(input));

        assertEquals(3, movieList.size());
        assertRatedMovie(movieList.get(0), "Star Wars", Category.SCIFI, new Rating(4));
        assertRatedMovie(movieList.get(1), "Star Trek", Category.UNCATEGORIZED, new Rating(3));
        assertUnratedMovie(movieList.get(2), "The Fellowship of The Ring", Category.FANTASY);
    }
}
