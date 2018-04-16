package es.montanus.movie2see.tests;

import es.montanus.movie2see.Movie;
import es.montanus.movie2see.MovieList;
import es.montanus.movie2see.MovieListEditor;
import es.montanus.movie2see.MovieListEditorView;
import junit.framework.TestCase;
import org.easymock.EasyMock;
import org.easymock.MockControl;

import java.util.Vector;

public class TestMovieListEditor extends TestCase {
    private Movie starWars;
    private Movie starTrek;

    public void setUp() throws Exception {
        super.setUp();
        starWars = new Movie("Star Wars");
        starTrek = new Movie("Star Trek");
    }

    public void testList() {

        MovieList movieList = getMovieList();

        MockControl control = EasyMock.controlFor(MovieListEditorView.class);
        MovieListEditorView mockView = (MovieListEditorView)control.getMock();

        mockView.setMovies(getMovieVector());
        control.setVoidCallable(1);

        control.activate();
        new MovieListEditor(movieList, mockView);
        control.verify();
    }

    private Vector<Movie> getMovieVector() {
        Vector<Movie> movieVector = new Vector<Movie>();
        movieVector.add(starWars);
        movieVector.add(starTrek);
        return movieVector;
    }

    private MovieList getMovieList() {
        MovieList movieList = new MovieList();
        movieList.add(starWars);
        movieList.add(starTrek);
        return movieList;
    }
}
