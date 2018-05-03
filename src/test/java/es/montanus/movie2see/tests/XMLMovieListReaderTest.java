package es.montanus.movie2see.tests;

import es.montanus.movie2see.*;
import junit.framework.TestCase;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class XMLMovieListReaderTest extends TestCase {

    private static final String EMPTY_STRING = "<movielist />";

    public void testReadEmptyList() {
        Reader reader = new StringReader(EMPTY_STRING);
        MovieList movies = new XMLMovieListReader(reader).read();
        assertEquals(0, movies.size());
    }

    public void testReadOneUncategorizedMovieWithoutRatings() {
        final String movieName = "Movie Name";
        final Category movieCategory = Category.UNCATEGORIZED;
        final String oneMovieString = "<movielist>" +
                "<movie name=\"" + movieName + "\" category=\"" + movieCategory.toString() + "\" />" +
                "</movielist>";
        Reader reader = new StringReader(oneMovieString);
        MovieList movies = new XMLMovieListReader(reader).read();
        assertEquals(1, movies.size());
        final Movie movie = movies.get(0);
        assertEquals(movieName, movie.getName());
        assertEquals(movieCategory, movie.getCategory());
    }

    public void testReadOneCategorizedMovieWithRatings() {
        final String movieName = "Movie Name";
        final Category movieCategory = Category.SCIFI;
        final String content = "<movielist>" +
                "<movie name=\"" + movieName + "\" category=\"" + movieCategory.toString() + "\">" +
                "<ratings>" +
                "<rating value=\"4\" source=\"News\" />" +
                "<rating value=\"3\" source=\"Anonymous\" />" +
                "</ratings>" +
                "</movie>" +
                "</movielist>";
        Reader reader = new StringReader(content);
        MovieList movies = new XMLMovieListReader(reader).read();
        assertEquals(1, movies.size());
        final Movie movie = movies.get(0);
        assertEquals(movieName, movie.getName());
        assertEquals(movieCategory, movie.getCategory());
        List<Rating> ratings = new ArrayList<>();
        for (Rating rating: movie)
            ratings.add(rating);
        assertEquals(2, ratings.size());
    }

    public void testReadMultipleMovies() {
        final String content = "<movielist>" +
                "<movie name=\"Star Wars\" category=\"Science Fiction\">" +
                "<ratings>" +
                "<rating value=\"4\" source=\"News\" />" +
                "<rating value=\"3\" source=\"Anonymous\" />" +
                "</ratings>" +
                "</movie>" +
                "<movie name=\"Star Trek\" category=\"Uncategorized\">" +
                "</movie>" +
                "</movielist>";
        Reader reader = new StringReader(content);
        MovieList movies = new XMLMovieListReader(reader).read();
        assertEquals(2, movies.size());
    }
}
