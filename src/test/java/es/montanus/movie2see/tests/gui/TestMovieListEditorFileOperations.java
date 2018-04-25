package es.montanus.movie2see.tests.gui;

import es.montanus.movie2see.gui.MovieListEditor;
import es.montanus.movie2see.tests.FileAssert;

import java.io.File;
import java.io.IOException;

public class TestMovieListEditorFileOperations extends BaseTestMovieListEditor {

    private File outputFile;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        outputFile = File.createTempFile("testSaveAs", ".dat");
        outputFile.deleteOnExit();
    }

    public void testSaving() throws IOException {
        mockView.chooseFile("*.dat");
        control.setReturnValue(outputFile, 1);

        control.activate();
        MovieListEditor editor = new MovieListEditor(getMovieList(), mockView);
        assertTrue(editor.saveAs());
        control.verify();

        final String expected =
                "Star Wars|Science Fiction|5\n" +
                "Star Trek|Uncategorized|-1\n";
        FileAssert.assertSize(expected.length(), outputFile);
        FileAssert.assertEquals(expected, outputFile);
    }

    public void testCancelledSaving() throws IOException {
        mockView.chooseFile("*.dat");
        control.setReturnValue(null);

        control.activate();
        MovieListEditor editor = new MovieListEditor(getMovieList(), mockView);
        assertFalse(editor.saveAs());
        control.verify();
    }
}
