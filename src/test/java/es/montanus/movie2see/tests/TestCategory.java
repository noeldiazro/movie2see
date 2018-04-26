package es.montanus.movie2see.tests;

import es.montanus.movie2see.Category;
import junit.framework.TestCase;

public class TestCategory extends TestCase {
    public void testGetCategoryFromTextRepresentation() {
        assertEquals(Category.SCIFI, Category.from("Science Fiction"));
        assertEquals(Category.UNCATEGORIZED, Category.from("Uncategorized"));
    }

    public void testUnexistingCategory() {
        try {
            Category.from("XYZ");
            fail("IllegalArgumentException should have been thrown");
        }
        catch (IllegalArgumentException expected) {
            assertEquals(Category.NO_CATEGORY_MSG, expected.getMessage());
        }
    }
}
