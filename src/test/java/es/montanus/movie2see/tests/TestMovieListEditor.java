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
    private MockControl control;
    private MovieListEditorView mockView;

    public void setUp() throws Exception {
        super.setUp();
        starWars = new Movie("Star Wars");
        starTrek = new Movie("Star Trek");
        lostInSpace = new Movie("Lost in Space");

        control = EasyMock.controlFor(MovieListEditorView.class);
        mockView = (MovieListEditorView) control.getMock();

        mockView.setMovies(getMovieVector());
        control.setVoidCallable(1);
        mockView.setEditor(null);
        control.setDefaultVoidCallable();
    }

    public void testList() {
        control.activate();
        new MovieListEditor(getMovieList(), mockView);
        control.verify();
    }

    public void testAdding() {
        mockView.getNewName();
        control.setReturnValue("Lost in Space");
        mockView.setMovies(getMovieVectorWithAddition());
        control.setVoidCallable(1);

        control.activate();
        MovieListEditor editor = new MovieListEditor(getMovieList(), mockView);
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

    public void testSelecting() {
        mockView.setNewName("Star Trek");
        control.setVoidCallable(1);
        mockView.setNewName("Star Wars");
        control.setVoidCallable(1);

        control.activate();
        MovieListEditor editor = new MovieListEditor(getMovieList(), mockView);
        editor.select(1);
        editor.select(0);
        control.verify();
    }


    /*
    public void testUpdating() {
        Vector<Movie> newMovies = new Vector<Movie>();
        newMovies.add(starWars);
        newMovies.add(new Movie("Star Trek I"));


        mockView.setNewName("Star Trek");
        control.setVoidCallable(1);

        mockView.getNewName();
        control.setReturnValue("Star Trek I", 1);

        mockView.setMovies(newMovies);
        control.setVoidCallable(1);

        control.activate();
        MovieListEditor editor = new MovieListEditor(getMovieList(), mockView);
        editor.select(1);
        editor.update();
        control.verify();
    }
    */
}
