package es.montanus.movie2see.tests;

import junit.framework.Assert;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileAssert {

    public static void assertSize(int length, File outputFile) {
        Assert.assertEquals(length, outputFile.length());
    }

    public static void assertEquals(String expected, File outputFile) throws IOException {
        try (FileReader reader = new FileReader(outputFile)) {
            for (int charIndex = 0; charIndex < expected.length(); charIndex++)
                Assert.assertEquals(getMessage(charIndex), expected.charAt(charIndex), (char) reader.read());
        }
    }

    private static String getMessage(int charIndex) {
        return String.format("File has wrong character at index %d", charIndex);
    }
}
