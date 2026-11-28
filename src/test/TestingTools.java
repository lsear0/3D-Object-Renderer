package src.test;

import static org.junit.Assert.fail;

import src.Matrix;

public class TestingTools {
    public static void compare(Matrix actual, Matrix expected, String message) {
        if (actual.getLength() != expected.getLength()) {
            fail("Length doesn't match");
        }

        if (actual.getWidth() != expected.getWidth()) {
            fail("Width doesn't match");
        }

        for (int i = 0; i < actual.getLength(); i++) {
            for (int j = 0; j < actual.getWidth(); j++) {
                if (actual.getValue(j, i) != expected.getValue(j, i)) {
                    fail(message);
                }
            }
        }
    }
}
