package src.test;

// JUnit
import static org.junit.jupiter.api.Assertions.assertEquals;
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

        for (int i = 0; i < sum.getLength(); i++) {
            for (int j = 0; j < sum.getWidth(); j++) {
                assertEquals(sum.getValue(j, i), expected.getValue(j, i));
            }
        }
    }

    @Test
    void subtractionTest () {
        Matrix a = new Matrix(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        Matrix b = new Matrix(new int[][]{{18, 17, 16}, {15, 14, 13}, {12, 11, 10}});

        MatrixOperator op = new MatrixOperator();

        Matrix aMinusB = op.subtract(a, b);
        Matrix aMinusB_expected = new Matrix(new int[][]{{-17, -15, -13}, {-11, -9, -7}, {-5, -3, -1}});

        for (int i = 0; i < aMinusB.getLength(); i++) {
            for (int j = 0; j < aMinusB.getWidth(); j++) {
                assertEquals(aMinusB.getValue(j, i), aMinusB_expected.getValue(j, i));
            }
        }

        Matrix bMinusA = op.subtract(b, a);
        Matrix bMinusA_expected = new Matrix(new int[][]{{17, 15, 13}, {11, 9, 7}, {5, 3, 1}});
        for (int i = 0; i < bMinusA.getLength(); i++) {
            for (int j = 0; j < bMinusA.getWidth(); j++) {
                assertEquals(bMinusA.getValue(j, i), bMinusA_expected.getValue(j, i));
            }
        }
    }
}
