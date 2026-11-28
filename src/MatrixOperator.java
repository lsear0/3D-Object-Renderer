package src;

public class MatrixOperator {
    public MatrixOperator() {};

    public Matrix add(Matrix a, Matrix b) {
        // reject invalid inputs
        if (a.getLength() != b.getLength() || a.getWidth() != b.getWidth()) {
            return null;
        }

        // add corresponding elements
        Matrix sum = new Matrix(a.getLength(), a.getWidth());
        for (int i = 0; i < a.getLength(); i++) {
            for (int j = 0; j < a.getWidth(); j++) {
                int val = a.getValue(j, i) + b.getValue(j, i);
                sum.setValue(j, i, val);
            }
        }

        return sum;
    }

    public Matrix subtract(Matrix a, Matrix b) {
        // reject invalid inputs
        if (a.getLength() != b.getLength() || a.getWidth() != b.getWidth()) {
            return null;
        }

        // subtract corresponding elements
        Matrix difference = new Matrix(a.getLength(), a.getWidth());
        for (int i = 0; i < a.getLength(); i++) {
            for (int j = 0; j < a.getWidth(); j++) {
                int val = a.getValue(j, i) - b.getValue(j, i);
                difference.setValue(j, i, val);
            }
        }

        return difference;
    }

    // naive O(n^3) approach
    public Matrix multiply(Matrix a, Matrix b) {
        // reject invalid inputs
        if (a.getWidth() != b.getLength()) {
            return null;
        }

        // the product of two matrices with dimensions m x n and n x k has dimensions m x k
        Matrix product = new Matrix(a.getLength(), b.getWidth());

        // multiply matrices
        for (int i = 0; i < product.getLength(); i++) {
            for (int j = 0; j < product.getWidth(); j++) {
                int val = 0;
                for (int k = 0; k < a.getWidth(); k++) {
                    val += a.getValue(k, i) * b.getValue(j, k);
                }
                product.setValue(j, i, val);
            }
        }

        return product;
    }
    
    // Strassen's fast matrix multiplication algorithm
    public Matrix strassenMultiply(Matrix a, Matrix b) {
        // reject invalid inputs
        if (a.getWidth() != b.getLength()) {
            return null;
        }

        // initialize submatrices
        Matrix a11 = strassenSplit(strassenSplit(a, true)[0], false)[0]; // upper left quadrant
        Matrix a12 = strassenSplit(strassenSplit(a, true)[0], false)[1]; // upper right quadrant
        Matrix a21 = strassenSplit(strassenSplit(a, true)[1], false)[0]; // lower left quadrant
        Matrix a22 = strassenSplit(strassenSplit(a, true)[1], false)[1]; // lower right quadrant
        
        Matrix b11 = strassenSplit(strassenSplit(b, true)[0], false)[0]; // upper left quadrant
        Matrix b12 = strassenSplit(strassenSplit(b, true)[0], false)[1]; // upper rigth quadrant
        Matrix b21 = strassenSplit(strassenSplit(b, true)[1], false)[0]; // lower left quadrant
        Matrix b22 = strassenSplit(strassenSplit(b, true)[1], false)[1]; // lower right quadrant

        // calculate seven products
        Matrix M1 = multiply(add(a11, a22), add(b11, b22));         // (a11 + a22)(b11 + b22)
        Matrix M2 = multiply(add(a21, a22), b11);                   // (a21 + a22)b11
        Matrix M3 = multiply(a11, subtract(b12, b22));              // a11(b12 - b22)
        Matrix M4 = multiply(a22, subtract(b21, b11));              // a22(b21 - b11)
        Matrix M5 = multiply(add(a11, a12), b22);                   // (a11 + a12)b22
        Matrix M6 = multiply(subtract(a21, a11), add(b11, b12));    // (a21 - a11)(b11 + b12)
        Matrix M7 = multiply(subtract(a12, a22), add(b21, b22));    // (a12 - a22)(b21 + b22)

        // perform arithmetic on submatrices to get four quadrants
        Matrix Q1 = add(subtract(add(M1, M4), M5), M7);     // M1 + M4 - M5 + M7
        Matrix Q2 = add(M3, M5);                            // M3 + M5
        Matrix Q3 = add(M2, M4);                            // M2 + M4
        Matrix Q4 = add(add(subtract(M1, M2), M3), M6);     // M1 - M2 + M3 + M6

        // combine quadrants
        Matrix untrimmedProduct = append(append(Q1, Q2, false), append(Q3, Q4, false), true);

        // trim extra zeroes
        Matrix trimmedProduct = new Matrix(a.getLength(), b.getWidth());
        for (int i = 0; i < trimmedProduct.getLength(); i++) {
            for (int j = 0; j < trimmedProduct.getWidth(); j++) {
                trimmedProduct.setValue(j, i, untrimmedProduct.getValue(j, i));
            }
        }

        return trimmedProduct;
    }

    public Matrix append(Matrix a, Matrix b, boolean lengthwise) {
        // reject invalid inputs
        if (lengthwise && a.getWidth() != b.getWidth()) {
            return null;
        }
        if (!lengthwise && a.getLength() != b.getLength()) {
            return null;
        }

        Matrix appended;

        if (lengthwise) { // append b to the bottom of a
            appended = new Matrix(a.getLength() + b.getLength(), a.getWidth());

            // add a's values
            for (int i = 0; i < a.getLength(); i++) {
                for (int j = 0; j < a.getWidth(); j++) {
                    appended.setValue(j, i, a.getValue(j, i));
                }
            }

            // add b's values
            for (int i = 0; i < b.getLength(); i++) {
                for (int j = 0; j < b.getWidth(); j++) {
                    appended.setValue(j, i + a.getLength(), b.getValue(j, i));
                }
            }
        } else { // append b to the right of a
            appended = new Matrix(a.getLength(), a.getWidth() + b.getWidth());

            // add a's values
            for (int i = 0; i < a.getLength(); i++) {
                for (int j = 0; j < a.getWidth(); j++) {
                    appended.setValue(j, i, a.getValue(j, i));
                }
            }

            // add b's values
            for (int i = 0; i < b.getLength(); i++) {
                for (int j = 0; j < b.getWidth(); j++) {
                    appended.setValue(j + a.getLength(), i, b.getValue(j, i));
                }
            }
        }

        return appended;
    }

    /*
    Splits a matrix into two submatrices.

    "index" is the point at which the split occurs. The first submatrix will cover everything up to
    and including that index, and the second submatrix will cover everything afterwards. Note that
    it is 0-indexed, so index = 0 still splits the top row or column from the rest of the matrix.
    */
    public Matrix[] split(Matrix matrix, boolean lengthwise, int index) {
        // reject invalid inputs
        if (index < 0) {
            return new Matrix[]{null, matrix};
        }
        if (lengthwise && index >= matrix.getLength()) {
            return new Matrix[]{matrix, null};
        }
        if (!lengthwise && index >= matrix.getWidth()) {
            return new Matrix[]{matrix, null};
        }

        Matrix a;
        Matrix b;

        if (lengthwise) { // split b from the bottom of a
            a = new Matrix(index + 1, matrix.getWidth());
            b = new Matrix(matrix.getLength() - (index + 1), matrix.getWidth());

            // add a's values
            for (int i = 0; i < a.getLength(); i++) {
                for (int j = 0; j < a.getWidth(); j++) {
                    a.setValue(j, i, matrix.getValue(j, i));
                }
            }

            // add b's values
            for (int i = 0; i < b.getLength(); i++) {
                for (int j = 0; j < b.getWidth(); j++) {
                    b.setValue(j, i, matrix.getValue(j, i + index + 1));
                }
            }
        } else { // split b from the right of a
            a = new Matrix(matrix.getLength(), index + 1);
            b = new Matrix(matrix.getLength(), matrix.getWidth() - (index + 1));

            // add a's values
            for (int i = 0; i < a.getLength(); i++) {
                for (int j = 0; j < a.getWidth(); j++) {
                    a.setValue(j, i, matrix.getValue(j, i));
                }
            }

            // add b's values
            for (int i = 0; i < b.getLength(); i++) {
                for (int j = 0; j < b.getWidth(); j++) {
                    b.setValue(j, i, matrix.getValue(j + index + 1, i));
                }
            }
        }

        return new Matrix[]{a, b};
    }

    /*
    Splits a matrix into two submatrices of equal length.

    If a matrix's length is not evenly divisible by 2, extra space is allocated for the second
    submatrix to ensure both submatrices are of equal size. The extra space is filled with zeroes.
    */
    public Matrix[] strassenSplit(Matrix matrix, boolean lengthwise) {
        Matrix a;
        Matrix b;

        if (lengthwise) { // split b from the bottom of a
            a = new Matrix((matrix.getLength() + 1) / 2, matrix.getWidth());
            b = new Matrix((matrix.getLength() + 1) / 2, matrix.getWidth());

            // add a's values
            for (int i = 0; i < a.getLength(); i++) {
                for (int j = 0; j < a.getWidth(); j++) {
                    a.setValue(j, i, matrix.getValue(j, i));
                }
            }

            // add b's values
            for (int i = a.getLength(); i < matrix.getLength(); i++) {
                for (int j = 0; j < b.getWidth(); j++) {
                    b.setValue(j, i - a.getLength(), matrix.getValue(j, i));
                }
            }
        } else { // split b from the right of a
            a = new Matrix(matrix.getLength(), (matrix.getWidth() + 1) / 2);
            b = new Matrix(matrix.getLength(), (matrix.getWidth() + 1) / 2);

            // add a's values
            for (int i = 0; i < a.getLength(); i++) {
                for (int j = 0; j < a.getWidth(); j++) {
                    a.setValue(j, i, matrix.getValue(j, i));
                }
            }

            // add b's values
            for (int i = 0; i < b.getLength(); i++) {
                for (int j = a.getWidth(); j < matrix.getWidth(); j++) {
                    b.setValue(j - a.getWidth(), i, matrix.getValue(j, i));
                }
            }
        }

        return new Matrix[]{a, b};
    }
}
