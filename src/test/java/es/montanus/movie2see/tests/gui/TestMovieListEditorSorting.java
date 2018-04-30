package es.montanus.movie2see.tests.gui;

import es.montanus.movie2see.Movie;
import es.montanus.movie2see.MovieNameComparator;
import es.montanus.movie2see.gui.MovieListEditor;

import java.util.Vector;

public class TestMovieListEditorSorting extends TestMovieListEditor {
    private Vector<Movie> sortedMovies;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        sortedMovies = new Vector<>();
        sortedMovies.add(starTrek);
        sortedMovies.add(starWars);
    }

    public void testSorting() {
        mockView.setMovies(sortedMovies);
        control.setVoidCallable(1);

        control.activate();
        MovieListEditor editor = new MovieListEditor(getMovieList(), mockView);
        editor.sortUsing(new MovieNameComparator());
        control.verify();
    }
}
