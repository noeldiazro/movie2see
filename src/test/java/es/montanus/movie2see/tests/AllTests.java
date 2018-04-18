package es.montanus.movie2see.tests;

import junit.framework.TestSuite;

public class AllTests extends TestSuite {
    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(TestEmptyMovieList.class);
        suite.addTestSuite(TestMovieListWithOneMovie.class);
        suite.addTestSuite(TestMovieListWithTwoMovies.class);
        suite.addTestSuite(TestMovieListEditor.class);
        suite.addTestSuite(TestUnratedMovie.class);
        return suite;
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(AllTests.suite());
    }
}
