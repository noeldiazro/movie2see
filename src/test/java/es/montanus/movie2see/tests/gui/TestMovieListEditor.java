package es.montanus.movie2see.tests.gui;

import es.montanus.movie2see.Category;
import es.montanus.movie2see.Movie;
import es.montanus.movie2see.gui.MovieListEditor;

import java.util.Vector;

public class TestMovieListEditor extends BaseTestMovieListEditor {

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
        result.add(new Movie.Builder("Lost in Space").build());
        return result;
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
        control.setDefaultVoidCallable();

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
