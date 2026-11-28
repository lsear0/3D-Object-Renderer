package src.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import src.Matrix;
import src.MatrixOperator;

public class AppendSplitTest {
    @Test
    void appendTest () {
        Matrix a = new Matrix(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        Matrix b = new Matrix(new int[][]{{10, 11, 12}, {13, 14, 15}, {16, 17, 18}});

        MatrixOperator op = new MatrixOperator();

        // Lengthwise append test
        Matrix abLengthwise = op.append(a, b, true);
        Matrix abLengthwise_expected = new Matrix(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}, {13, 14, 15}, {16, 17, 18}});

        for (int i = 0; i < abLengthwise.getLength(); i++) {
            for (int j = 0; j < abLengthwise.getWidth(); j++) {
                assertEquals(abLengthwise.getValue(j, i), abLengthwise_expected.getValue(j, i), "A append B lengthwise failed");
            }
        }

        Matrix baLengthwise = op.append(b, a, true);
        Matrix baLengthwise_expected = new Matrix(new int[][]{{10, 11, 12}, {13, 14, 15}, {16, 17, 18}, {1, 2, 3}, {4, 5, 6}, {7, 8, 9}});

        for (int i = 0; i < baLengthwise.getLength(); i++) {
            for (int j = 0; j < baLengthwise.getWidth(); j++) {
                assertEquals(baLengthwise.getValue(j, i), baLengthwise_expected.getValue(j, i), "B append A lengthwise failed");
            }
        }

        // Widthwise append test
        Matrix abWidthwise = op.append(a, b, false);
        Matrix abWidthwise_expected = new Matrix(new int[][]{{1, 2, 3, 10, 11, 12}, {4, 5, 6, 13, 14, 15}, {7, 8, 9, 16, 17, 18}});

        for (int i = 0; i < abWidthwise.getLength(); i++) {
            for (int j = 0; j < abWidthwise.getWidth(); j++) {
                assertEquals(abWidthwise.getValue(j, i), abWidthwise_expected.getValue(j, i), "A append B widthwise failed");
            }
        }
    }

    @Test
    void splitTest () {
        Matrix a = new Matrix(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}});

        MatrixOperator op = new MatrixOperator();

        // Lengthwise split test
        Matrix[] splitLengthwise = op.split(a, true, 1);
        Matrix a1 = splitLengthwise[0];
        Matrix a1_expected = new Matrix(new int[][]{{1, 2, 3}, {4, 5, 6}});

        for (int i = 0; i < a1.getLength(); i++) {
            for (int j = 0; j < a1.getWidth(); j++) {
                assertEquals(a1.getValue(j, i), a1_expected.getValue(j, i), "Lengthwise split failed on submatrix 1");
            }
        }

        Matrix a2 = splitLengthwise[1];
        Matrix a2_expected = new Matrix(new int[][]{{7, 8, 9}, {10, 11, 12}});

        for (int i = 0; i < a2.getLength(); i++) {
            for (int j = 0; j < a2.getWidth(); j++) {
                assertEquals(a2.getValue(j, i), a2_expected.getValue(j, i), "Lengthwise split failed on submatrix 2");
            }
        }

        // Widthwise split test
        Matrix[] splitWidthwise = op.split(a, false, 1);
        Matrix b1 = splitWidthwise[0];
        Matrix b1_expected = new Matrix(new int[][]{{1, 2}, {4, 5}, {7, 8}, {10, 11}});

        for (int i = 0; i < b1.getLength(); i++) {
            for (int j = 0; j < b1.getWidth(); j++) {
                assertEquals(b1.getValue(j, i), b1_expected.getValue(j, i), "Widthwise split failed on submatrix 1");
            }
        }

        Matrix b2 = splitWidthwise[1];
        Matrix b2_expected = new Matrix(new int[][]{{3}, {6}, {9}, {12}});

        for (int i = 0; i < b2.getLength(); i++) {
            for (int j = 0; j < b2.getWidth(); j++) {
                assertEquals(b2.getValue(j, i), b2_expected.getValue(j, i), "Widthwise split failed on submatrix 2");
            }
        }
    }
}
