package src.test;

import org.junit.jupiter.api.Test;

import src.Matrix;
import src.MatrixOperator;

public class AddSubtractTest {
    @Test
    void additionTest () {
        Matrix a = new Matrix(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        Matrix b = new Matrix(new int[][]{{10, 11, 12}, {13, 14, 15}, {16, 17, 18}});

        MatrixOperator op = new MatrixOperator();

        Matrix sum = op.add(a, b);
        Matrix expected = new Matrix(new int[][]{{11, 13, 15}, {17, 19, 21}, {23, 25, 27}});

        TestingTools.compare(sum, expected, "Incorrect sum");
    }

    @Test
    void subtractionTest () {
        Matrix a = new Matrix(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        Matrix b = new Matrix(new int[][]{{18, 17, 16}, {15, 14, 13}, {12, 11, 10}});

        MatrixOperator op = new MatrixOperator();

        // A - B test
        Matrix aMinusB = op.subtract(a, b);
        Matrix aMinusB_expected = new Matrix(new int[][]{{-17, -15, -13}, {-11, -9, -7}, {-5, -3, -1}});

        TestingTools.compare(aMinusB, aMinusB_expected, "Incorrect difference for A - B");

        // B - A test
        Matrix bMinusA = op.subtract(b, a);
        Matrix bMinusA_expected = new Matrix(new int[][]{{17, 15, 13}, {11, 9, 7}, {5, 3, 1}});

        TestingTools.compare(bMinusA, bMinusA_expected, "Incorrect difference for B - A");
    }
}
