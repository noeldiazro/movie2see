package es.montanus.movie2see.tests.gui;

import es.montanus.movie2see.Category;
import es.montanus.movie2see.gui.MovieListEditor;
import es.montanus.movie2see.tests.FileAssert;

import java.io.File;
import java.io.IOException;

public class TestMovieListEditorSavingOperations extends BaseTestMovieListEditor {

    private File outputFile;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        outputFile = File.createTempFile("testSaveAs", ".dat");
        outputFile.deleteOnExit();
    }

    public void testSaving() throws IOException {
        mockView.chooseSaveFile("*.dat");
        control.setReturnValue(outputFile, 1);

        mockView.getNameField();
        control.setReturnValue("The Fellowship of The Ring");
        mockView.getRatingField();
        control.setReturnValue(6);
        mockView.getCategoryField();
        control.setReturnValue(Category.FANTASY);

        control.activate();
        MovieListEditor editor = new MovieListEditor(getMovieList(), mockView);
        assertTrue(editor.saveAs());
        final String expectedBeforeAdding = "<movielist>" +
                "<movie name=\"Star Wars\" category=\"Science Fiction\">" +
                "<ratings>" +
                "<rating value=\"5\" source=\"Anonymous\" />" +
                "</ratings>" +
                "</movie>" +
                "<movie name=\"Star Trek\" category=\"Uncategorized\">" +
                "</movie>" +
                "</movielist>";
        FileAssert.assertSize(expectedBeforeAdding.length(), outputFile);
        FileAssert.assertEquals(expectedBeforeAdding, outputFile);

        editor.add();

        assertTrue(editor.save());
        final String expectedAfterAdding = "<movielist>" +
                "<movie name=\"Star Wars\" category=\"Science Fiction\">" +
                "<ratings>" +
                "<rating value=\"5\" source=\"Anonymous\" />" +
                "</ratings>" +
                "</movie>" +
                "<movie name=\"Star Trek\" category=\"Uncategorized\">" +
                "</movie>" +
                "<movie name=\"The Fellowship of The Ring\" category=\"Fantasy\">" +
                "<ratings>" +
                "<rating value=\"5\" source=\"Anonymous\" />" +
                "</ratings>" +
                "</movie>" +
                "</movielist>";
        FileAssert.assertSize(expectedAfterAdding.length(), outputFile);
        FileAssert.assertEquals(expectedAfterAdding, outputFile);
        control.verify();
    }

    public void testCancelledSaving() throws IOException {
        mockView.chooseSaveFile("*.dat");
        control.setReturnValue(null);

        control.activate();
        MovieListEditor editor = new MovieListEditor(getMovieList(), mockView);
        assertFalse(editor.saveAs());
        assertFalse(editor.save());
        control.verify();
    }
}
