package es.montanus.movie2see.tests.gui;

import es.montanus.movie2see.Category;
import es.montanus.movie2see.Movie;
import es.montanus.movie2see.MovieList;
import es.montanus.movie2see.Rating;
import es.montanus.movie2see.gui.MovieListEditorView;
import junit.framework.TestCase;
import org.easymock.EasyMock;
import org.easymock.MockControl;

import java.util.Vector;

public abstract class BaseTestMovieListEditor extends TestCase {
    protected Movie starWars;
    protected Movie starTrek;
    protected MockControl control;
    protected MovieListEditorView mockView;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        initMovies();

        control = EasyMock.controlFor(MovieListEditorView.class);
        mockView = (MovieListEditorView) control.getMock();

        mockView.setEditor(null);
        control.setDefaultVoidCallable();

        mockView.setMovies(getMovieVector());
        control.setDefaultVoidCallable();
    }

    private void initMovies() {
        starWars = new Movie.Builder("Star Wars").
                setRating(new Rating(5)).
                setCategory(Category.SCIFI).
                build();
        starTrek = new Movie.Builder("Star Trek").build();
    }

    protected Vector<Movie> getMovieVector() {
        Vector<Movie> movieVector = new Vector<>();
        movieVector.add(starWars);
        movieVector.add(starTrek);
        return movieVector;
    }

    protected MovieList getMovieList() {
        MovieList movieList = new MovieList();
        movieList.add(starWars);
        movieList.add(starTrek);
        return movieList;
    }
}
