package es.montanus.movie2see.tests.gui;

import es.montanus.movie2see.Category;
import es.montanus.movie2see.Movie;
import es.montanus.movie2see.Rating;
import es.montanus.movie2see.gui.MovieListEditor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class TestMovieListEditorOpeningOperations extends BaseTestMovieListEditor {
    private File inputFile;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        inputFile = File.createTempFile("opening", "dat");
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(inputFile))) {
            bufferedWriter.write("The Princess Bride|Fantasy|4|2\n");
            bufferedWriter.write("The Shining|Horror|-1|0\n");
            bufferedWriter.write("Jurasic Park|Uncategorized|1|1\n");
        }
    }

    public void testOpening() throws IOException {
        mockView.chooseOpenFile("*.dat");
        control.setReturnValue(inputFile);
        mockView.setMovies(getExpectedMoviesAfterOpen());
        control.activate();
        MovieListEditor editor = new MovieListEditor(getMovieList(), mockView);
        editor.open();
        control.verify();
    }

    private Vector<Movie> getExpectedMoviesAfterOpen() {
        Vector<Movie> result = new Vector<>();
        result.add(new Movie.Builder("The Princess Bride").setCategory(Category.FANTASY).setRating(new Rating(4)).build());
        result.add(new Movie.Builder("The Shining").setCategory(Category.HORROR).build());
        result.add(new Movie.Builder("Jurasic Park").setRating(new Rating(1)).build());
        return result;
    }
}
