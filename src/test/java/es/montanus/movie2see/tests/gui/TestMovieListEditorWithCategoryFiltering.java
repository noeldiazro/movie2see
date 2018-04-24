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

public class TestMovieListEditorWithCategoryFiltering extends TestCase {

    private Movie starWars;
    private Movie starTrek;
    private Movie stargate;
    private Movie theShining;
    private Movie carrie;
    private Movie fotr;
    private Movie redOctober;
    private Movie congo;
    private Movie princessBride;

    private MovieList movieList;
    private MovieList scifiList;
    private MovieList fantasyList;

    private Vector<Movie> movies;
    private Vector<Movie> scifiMovies;
    private Vector<Movie> fantasyMovies;

    private MockControl control;
    private MovieListEditorView mockView;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        starWars = new Movie.Builder("Star Wars").setCategory(Category.SCIFI).setRating(new Rating(5)).build();
        starTrek = new Movie.Builder("Star Trek").setCategory(Category.SCIFI).setRating(new Rating(3)).build();
        stargate = new Movie.Builder("Stargate").setCategory(Category.SCIFI).build();
        theShining = new Movie.Builder("The Shining").setCategory(Category.HORROR).setRating(new Rating(2)).build();
        carrie = new Movie.Builder("Carrie").setCategory(Category.HORROR).setRating(new Rating(3)).build();
        fotr = new Movie.Builder("The Fellowship of The Ring").setCategory(Category.FANTASY).setRating(new Rating(5)).build();
        redOctober = new Movie.Builder("The Hunt For Red October").setCategory(Category.THRILLER).setRating(new Rating(3)).build();
        congo = new Movie.Builder("Congo").setCategory(Category.THRILLER).setRating(new Rating(3)).build();
        princessBride = new Movie.Builder("The Princess Bride").setCategory(Category.FANTASY).setRating(new Rating(5)).build();

        movieList = new MovieList();
        movieList.add(starWars);
        movieList.add(starTrek);
        movieList.add(stargate);
        movieList.add(theShining);
        movieList.add(carrie);
        movieList.add(fotr);
        movieList.add(redOctober);
        movieList.add(congo);
        movieList.add(princessBride);
        movies = movieList.toVector();

        scifiList = new MovieList();
        scifiList.add(starWars);
        scifiList.add(starTrek);
        scifiList.add(stargate);
        scifiMovies = scifiList.toVector();

        fantasyList = new MovieList();
        fantasyList.add(fotr);
        fantasyList.add(princessBride);
        fantasyMovies = fantasyList.toVector();

        control = EasyMock.controlFor(MovieListEditorView.class);
        mockView = (MovieListEditorView) control.getMock();
        mockView.setEditor(null);
        control.setDefaultVoidCallable();
        mockView.setMovies(movies);
        control.setVoidCallable(1);
    }

    public void testCategoryFiltering() {
        mockView.setMovies(scifiMovies);
        control.setVoidCallable(1);

        mockView.setMovies(movies);
        control.setVoidCallable(1);

        control.activate();

        MovieListEditor editor = new MovieListEditor(movieList, mockView);
        editor.filterOn(Category.SCIFI);
        editor.filterOn(Category.ALL);

        control.verify();
    }

    public void testSelecting() {
        mockView.setMovies(fantasyMovies);
        control.setVoidCallable(1);

        mockView.setNameField(fotr.getName());
        control.setVoidCallable(1);
        mockView.setRatingField(6);
        control.setVoidCallable(1);
        mockView.setCategoryField(Category.FANTASY);
        control.setVoidCallable(1);

        control.activate();

        MovieListEditor editor = new MovieListEditor(movieList, mockView);
        editor.filterOn(Category.FANTASY);
        editor.select(0);

        control.verify();
    }

    public void testUpdating() {
        mockView.setMovies(fantasyMovies);
        control.setVoidCallable(1);

        mockView.setNameField(princessBride.getName());
        control.setVoidCallable();
        mockView.setRatingField(6);
        control.setVoidCallable(1);
        mockView.setCategoryField(Category.FANTASY);
        control.setVoidCallable(1);

        mockView.getNameField();
        control.setReturnValue(princessBride.getName());
        mockView.getRatingField();
        control.setReturnValue(5);
        mockView.getCategoryField();
        control.setReturnValue(Category.FANTASY);

        mockView.setMovies(fantasyMovies);
        control.setVoidCallable(1);

        control.activate();

        MovieListEditor editor = new MovieListEditor(movieList, mockView);
        editor.filterOn(Category.FANTASY);
        editor.select(1);
        editor.update();

        control.verify();
        assertEquals(new Rating(4), princessBride.getRating());
    }

    public void testDuplicateCausingUpdate() {
        mockView.setMovies(fantasyMovies);
        control.setVoidCallable(1);

        mockView.setNameField(princessBride.getName());
        control.setVoidCallable();
        mockView.setRatingField(6);
        control.setVoidCallable(1);
        mockView.setCategoryField(Category.FANTASY);
        control.setVoidCallable(1);

        mockView.getNameField();
        control.setReturnValue(starWars.getName());
        mockView.duplicateException(starWars.getName());
        control.setVoidCallable(1);

        control.activate();

        MovieListEditor editor = new MovieListEditor(movieList, mockView);
        editor.filterOn(Category.FANTASY);
        editor.select(1);
        editor.update();

        control.verify();
    }

    public void testChangingCategory() {
        mockView.setMovies(fantasyMovies);
        control.setVoidCallable(1);

        mockView.setNameField(princessBride.getName());
        control.setVoidCallable();
        mockView.setRatingField(6);
        control.setVoidCallable(1);
        mockView.setCategoryField(Category.FANTASY);
        control.setVoidCallable(1);

        mockView.getNameField();
        control.setReturnValue(princessBride.getName());
        mockView.getRatingField();
        control.setReturnValue(5);
        mockView.getCategoryField();
        control.setReturnValue(Category.COMEDY);

        Vector<Movie> newFantasyMovies = new Vector<Movie>();
        newFantasyMovies.add(fotr);
        mockView.setMovies(newFantasyMovies);
        control.setVoidCallable(1);

        Vector<Movie> comedyMovies = new Vector<Movie>();
        comedyMovies.add(princessBride);
        mockView.setMovies(comedyMovies);
        control.setVoidCallable(1);

        control.activate();

        MovieListEditor editor = new MovieListEditor(movieList, mockView);
        editor.filterOn(Category.FANTASY);
        editor.select(1);
        editor.update();
        editor.filterOn(Category.COMEDY);
        control.verify();
    }
}
