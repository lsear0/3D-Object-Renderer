package src.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import src.Matrix;
import src.MatrixOperator;

public class MultiplicationTest {
    @Test
    void additionTest () {
        Matrix a = new Matrix(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        Matrix b = new Matrix(new int[][]{{10, 11, 12}, {13, 14, 15}, {16, 17, 18}});

        MatrixOperator op = new MatrixOperator();

        Matrix aTimesB = op.multiply(a, b);
        Matrix aTimesB_expected = new Matrix(new int[][]{{84, 90, 96}, {201, 216, 231}, {318, 342, 366}});

        for (int i = 0; i < aTimesB.getLength(); i++) {
            for (int j = 0; j < aTimesB.getWidth(); j++) {
                assertEquals(aTimesB.getValue(j, i), aTimesB_expected.getValue(j, i), "A x B failed");
            }
        }

        Matrix bTimesA = op.multiply(b, a);
        Matrix bTimesA_expected = new Matrix(new int[][]{{138, 171, 204}, {174, 216, 258}, {210, 261, 312}});

        for (int i = 0; i < bTimesA.getLength(); i++) {
            for (int j = 0; j < bTimesA.getWidth(); j++) {
                assertEquals(bTimesA.getValue(j, i), bTimesA_expected.getValue(j, i), "B x A failed");
            }
        }
    }
}
