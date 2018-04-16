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
    private Movie lostInSpace;

    public void setUp() throws Exception {
        super.setUp();
        starWars = new Movie("Star Wars");
        starTrek = new Movie("Star Trek");
        lostInSpace = new Movie("Lost in Space");
    }

    public void testList() {
        MockControl control = EasyMock.controlFor(MovieListEditorView.class);
        MovieListEditorView mockView = (MovieListEditorView)control.getMock();

        mockView.setMovies(getMovieVector());
        control.setVoidCallable(1);
        mockView.setEditor(null);
        control.setDefaultVoidCallable();

        MovieList movieList = getMovieList();
        control.activate();
        new MovieListEditor(movieList, mockView);
        control.verify();
    }

    public void testAdding() {
        MockControl control = EasyMock.controlFor(MovieListEditorView.class);
        MovieListEditorView mockView = (MovieListEditorView)control.getMock();


        mockView.setMovies(getMovieVector());
        control.setVoidCallable(1);
        mockView.setEditor(null);
        control.setDefaultVoidCallable();
        mockView.getNewName();
        control.setReturnValue("Lost in Space");
        mockView.setMovies(getMovieVectorWithAddition());
        control.setVoidCallable(1);

        MovieList movieList = getMovieList();
        control.activate();
        MovieListEditor editor = new MovieListEditor(movieList, mockView);
        editor.add();
        control.verify();
    }

    private Vector<Movie> getMovieVectorWithAddition() {
        Vector<Movie> result = getMovieVector();
        result.add(lostInSpace);
        return result;
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
