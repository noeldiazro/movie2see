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
            bufferedWriter.write("<movielist>");

            bufferedWriter.write("<movie name=\"The Princess Bride\" category=\"Fantasy\">");
            bufferedWriter.write("<ratings>");
            bufferedWriter.write("<rating value=\"4\" source=\"Anonymous\" />");
            bufferedWriter.write("</ratings>");
            bufferedWriter.write("</movie>");

            bufferedWriter.write("<movie name=\"The Shining\" category=\"Horror\" />");

            bufferedWriter.write("<movie name=\"Jurasic Park\" category=\"Uncategorized\">");
            bufferedWriter.write("<ratings>");
            bufferedWriter.write("<rating value=\"1\" source=\"NY Times\" />");
            bufferedWriter.write("</ratings>");
            bufferedWriter.write("</movie>");

            bufferedWriter.write("</movielist>");
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
        final Movie princessBride = new Movie.Builder("The Princess Bride").setCategory(Category.FANTASY).build();
        princessBride.addRating(new Rating(4));
        final Movie theShining = new Movie.Builder("The Shining").setCategory(Category.HORROR).build();
        final Movie jurasicPark = new Movie.Builder("Jurasic Park").build();
        jurasicPark.addRating(new Rating(1, "NY Times"));
        result.add(princessBride);
        result.add(theShining);
        result.add(jurasicPark);
        return result;
    }
}
