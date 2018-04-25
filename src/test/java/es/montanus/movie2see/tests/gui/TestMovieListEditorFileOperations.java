package es.montanus.movie2see.tests.gui;

import es.montanus.movie2see.Category;
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

        mockView.getNameField();
        control.setReturnValue("The Fellowship of The Ring");
        mockView.getRatingField();
        control.setReturnValue(6);
        mockView.getCategoryField();
        control.setReturnValue(Category.FANTASY);

        control.activate();
        MovieListEditor editor = new MovieListEditor(getMovieList(), mockView);
        assertTrue(editor.saveAs());
        final String expectedBeforeAdding =
                "Star Wars|Science Fiction|5\n" +
                "Star Trek|Uncategorized|-1\n";
        FileAssert.assertSize(expectedBeforeAdding.length(), outputFile);
        FileAssert.assertEquals(expectedBeforeAdding, outputFile);

        editor.add();

        assertTrue(editor.save());
        final String expectedAfterAdding =
                expectedBeforeAdding +
                "The Fellowship of The Ring|Fantasy|5\n";
        FileAssert.assertSize(expectedAfterAdding.length(), outputFile);
        FileAssert.assertEquals(expectedAfterAdding, outputFile);
        control.verify();
    }

    public void testCancelledSaving() throws IOException {
        mockView.chooseFile("*.dat");
        control.setReturnValue(null);

        control.activate();
        MovieListEditor editor = new MovieListEditor(getMovieList(), mockView);
        assertFalse(editor.saveAs());
        assertFalse(editor.save());
        control.verify();
    }
}
