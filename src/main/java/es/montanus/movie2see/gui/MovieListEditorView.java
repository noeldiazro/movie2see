package es.montanus.movie2see.gui;

import es.montanus.movie2see.Movie;
import es.montanus.movie2see.gui.MovieListEditor;

import java.util.Vector;

public interface MovieListEditorView {
    void setMovies(Vector<Movie> movies);
    String getNewName();
    void setEditor(MovieListEditor editor);
    void setNewName(String newName);
    void duplicateException(String string);
}
