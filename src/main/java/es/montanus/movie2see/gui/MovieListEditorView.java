package es.montanus.movie2see.gui;

import es.montanus.movie2see.Category;
import es.montanus.movie2see.Movie;
import es.montanus.movie2see.gui.MovieListEditor;

import java.io.File;
import java.util.Vector;

public interface MovieListEditorView {
    void setMovies(Vector<Movie> movies);
    String getNameField();
    void setEditor(MovieListEditor editor);
    void setNameField(String string);
    void duplicateException(String string);
    void setRatingField(int value);
    int getRatingField();
    void setCategoryField(Category category);
    Category getCategoryField();
    File chooseFile(String pattern);
}
