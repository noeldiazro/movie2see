package es.montanus.movie2see.tests.gui;

import es.montanus.movie2see.Category;
import es.montanus.movie2see.Movie;
import es.montanus.movie2see.MovieList;
import es.montanus.movie2see.Rating;
import es.montanus.movie2see.gui.MovieListEditor;
import es.montanus.movie2see.gui.MovieListEditorView;
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
        starWars = new Movie.Builder("Star Wars").
                setRating(new Rating(5)).
                setCategory(Category.SCIFI).
                build();
        starTrek = new Movie.Builder("Star Trek").build();
        lostInSpace = new Movie.Builder("Lost in Space").build();

        control = EasyMock.controlFor(MovieListEditorView.class);
        mockView = (MovieListEditorView) control.getMock();

        mockView.setMovies(getMovieVector());
        control.setDefaultVoidCallable();
        mockView.setEditor(null);
        control.setDefaultVoidCallable();
    }

    public void testList() {
        control.activate();
        new MovieListEditor(getMovieList(), mockView);
        control.verify();
    }

    public void testAdding() {
        mockView.getNameField();
        control.setReturnValue("Lost in Space");
        mockView.getRatingField();
        control.setReturnValue(6);
        mockView.getCategoryField();
        control.setReturnValue(Category.SCIFI);

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
        mockView.setNameField("Star Wars");
        control.setVoidCallable(1);
        mockView.setRatingField(6);
        control.setVoidCallable(1);
        mockView.setCategoryField(Category.SCIFI);
        control.setVoidCallable(1);

        mockView.setNameField("Star Trek");
        control.setVoidCallable(1);
        mockView.setRatingField(0);
        control.setVoidCallable(1);
        mockView.setCategoryField(Category.UNCATEGORIZED);
        control.setVoidCallable();

        control.activate();
        MovieListEditor editor = new MovieListEditor(getMovieList(), mockView);
        editor.select(0);
        editor.select(1);
        control.verify();
    }

    public void testDuplicateCausingAdd() {
        mockView.getNameField();
        control.setReturnValue("Star Wars", 1);
        mockView.getRatingField();
        control.setReturnValue(6, 1);
        mockView.getCategoryField();
        control.setReturnValue(Category.SCIFI);

        mockView.duplicateException("Star Wars");
        control.setVoidCallable(1);

        control.activate();
        MovieListEditor editor = new MovieListEditor(getMovieList(), mockView);
        editor.add();
        control.verify();
    }

    public void testDuplicateCausingUpdate() {
        mockView.setNameField("Star Trek");
        control.setVoidCallable(1);
        mockView.setRatingField(0);
        control.setVoidCallable(1);
        mockView.setCategoryField(Category.UNCATEGORIZED);
        control.setVoidCallable(1);

        mockView.getNameField();
        control.setReturnValue("Star Wars", 1);

        mockView.duplicateException("Star Wars");
        control.setVoidCallable(1);

        control.activate();
        MovieListEditor editor = new MovieListEditor(getMovieList(), mockView);
        editor.select(1);
        editor.update();
        control.verify();
    }


    public void testUpdatingBothNameAndRating() {
        Vector<Movie> newMovies = new Vector<Movie>();
        newMovies.add(starWars);
        newMovies.add(new Movie.Builder("Star Trek I").build());

        mockView.setNameField("Star Trek");
        control.setVoidCallable(1);
        mockView.setRatingField(0);
        control.setVoidCallable(1);
        mockView.setCategoryField(Category.UNCATEGORIZED);
        control.setVoidCallable();

        mockView.getNameField();
        control.setReturnValue("Star Trek I", 1);
        mockView.getRatingField();
        control.setReturnValue(3);
        mockView.getCategoryField();
        control.setReturnValue(Category.COMEDY);

        mockView.setMovies(newMovies);
        control.setVoidCallable(1);

        control.activate();
        MovieListEditor editor = new MovieListEditor(getMovieList(), mockView);
        editor.select(1);
        editor.update();
        control.verify();
    }

    public void testUpdatingJustRating() {
        mockView.setNameField("Star Wars");
        control.setVoidCallable(1);
        mockView.setRatingField(6);
        control.setVoidCallable(1);
        mockView.setCategoryField(Category.SCIFI);
        control.setVoidCallable();

        mockView.getNameField();
        control.setReturnValue("Star Wars");
        mockView.getRatingField();
        control.setReturnValue(4);
        mockView.getCategoryField();
        control.setReturnValue(Category.SCIFI);

        mockView.setMovies(getMovieVector());
        control.setDefaultVoidCallable();

        control.activate();
        MovieListEditor editor = new MovieListEditor(getMovieList(), mockView);
        editor.select(0);
        editor.update();

        control.verify();
    }


}
