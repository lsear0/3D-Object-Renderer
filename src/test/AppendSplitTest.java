package src.test;

import org.junit.jupiter.api.Test;

import src.Matrix;
import src.MatrixOperator;

public class AppendSplitTest {
    @Test
    void appendTest () {
        Matrix a = new Matrix(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        Matrix b = new Matrix(new int[][]{{10, 11, 12}, {13, 14, 15}, {16, 17, 18}});

        MatrixOperator op = new MatrixOperator();

        // Lengthwise append test 1
        Matrix abLengthwise = op.append(a, b, true);
        Matrix abLengthwise_expected = new Matrix(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}, {13, 14, 15}, {16, 17, 18}});

        TestingTools.compare(abLengthwise, abLengthwise_expected, "A append B lengthwise failed");

        // Lengthwise append test 2
        Matrix baLengthwise = op.append(b, a, true);
        Matrix baLengthwise_expected = new Matrix(new int[][]{{10, 11, 12}, {13, 14, 15}, {16, 17, 18}, {1, 2, 3}, {4, 5, 6}, {7, 8, 9}});

        TestingTools.compare(baLengthwise, baLengthwise_expected, "B append A lengthwise failed");

        // Widthwise append test
        Matrix abWidthwise = op.append(a, b, false);
        Matrix abWidthwise_expected = new Matrix(new int[][]{{1, 2, 3, 10, 11, 12}, {4, 5, 6, 13, 14, 15}, {7, 8, 9, 16, 17, 18}});

        TestingTools.compare(abWidthwise, abWidthwise_expected, "A append B widthwise failed");
    }

    @Test
    void splitTest () {
        Matrix a = new Matrix(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}});

        MatrixOperator op = new MatrixOperator();

        // Lengthwise split test 1
        Matrix[] splitLengthwise = op.split(a, true, 1);
        Matrix a1 = splitLengthwise[0];
        Matrix a1_expected = new Matrix(new int[][]{{1, 2, 3}, {4, 5, 6}});

        TestingTools.compare(a1, a1_expected, "Lengthwise split failed on submatrix 1");

        // Lengthwise split test 2
        Matrix a2 = splitLengthwise[1];
        Matrix a2_expected = new Matrix(new int[][]{{7, 8, 9}, {10, 11, 12}});

        TestingTools.compare(a2, a2_expected, "Lengthwise split failed on submatrix 2");

        // Widthwise split test 1
        Matrix[] splitWidthwise = op.split(a, false, 1);
        Matrix b1 = splitWidthwise[0];
        Matrix b1_expected = new Matrix(new int[][]{{1, 2}, {4, 5}, {7, 8}, {10, 11}});

        TestingTools.compare(b1, b1_expected, "Widthwise split failed on submatrix 1");

        // Widthwise split test 2
        Matrix b2 = splitWidthwise[1];
        Matrix b2_expected = new Matrix(new int[][]{{3}, {6}, {9}, {12}});

        TestingTools.compare(b2, b2_expected, "Widthwise split failed on submatrix 2");
    }

    @Test
    void strassenSplitTest () {
        Matrix a = new Matrix(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});

        MatrixOperator op = new MatrixOperator();

        // Lengthwise split test 1
        Matrix[] splitLengthwise = op.strassenSplit(a, true);
        Matrix a1 = splitLengthwise[0];
        Matrix a1_expected = new Matrix(new int[][]{{1, 2, 3}, {4, 5, 6}});

        TestingTools.compare(a1, a1_expected, "Lengthwise split failed on submatrix 1");

        // Lengthwise split test 2
        Matrix a2 = splitLengthwise[1];
        Matrix a2_expected = new Matrix(new int[][]{{7, 8, 9}, {0, 0, 0}});

        TestingTools.compare(a2, a2_expected, "Lengthwise split failed on submatrix 2");

        // Widthwise split test 1
        Matrix[] splitWidthwise = op.strassenSplit(a, false);
        Matrix b1 = splitWidthwise[0];
        Matrix b1_expected = new Matrix(new int[][]{{1, 2}, {4, 5}, {7, 8}});

        TestingTools.compare(b1, b1_expected, "Widthwise split failed on submatrix 1");

        // Widthwise split test 2
        Matrix b2 = splitWidthwise[1];
        Matrix b2_expected = new Matrix(new int[][]{{3, 0}, {6, 0}, {9, 0}});

        TestingTools.compare(b2, b2_expected, "Widthwise split failed on submatrix 2");
    }
}
