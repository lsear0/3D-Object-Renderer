public class MatrixOperator {
    public MatrixOperator() {};

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
            for (int i = a.getLength(); i < a.getLength() + b.getLength(); i++) {
                for (int j = 0; j < b.getWidth(); j++) {
                    appended.setValue(j, i, b.getValue(j, i));
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
                for (int j = a.getWidth(); j < a.getWidth() + b.getWidth(); j++) {
                    appended.setValue(j, i, b.getValue(j, i));
                }
            }
        }

        return appended;
    }

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
    public Matrix fastMultiply(Matrix a, Matrix b) {
        // reject invalid inputs
        if (a.getWidth() != b.getLength()) {
            return null;
        }

        // the product of two matrices with dimensions m x n and n x k has dimensions m x k
        Matrix product = new Matrix(a.getLength(), b.getWidth());

        // initialize submatrices
        // TODO: implement the rest of the algorithm
        Matrix M1 = new Matrix(1, 1);
        Matrix M2 = new Matrix(1, 1);
        Matrix M3 = new Matrix(1, 1);
        Matrix M4 = new Matrix(1, 1);
        Matrix M5 = new Matrix(1, 1);
        Matrix M6 = new Matrix(1, 1);
        Matrix M7 = new Matrix(1, 1);

        // perform arithmetic on submatrices to get four quadrants
        Matrix Q1 = add(subtract(add(M1, M4), M5), M7); // M1 + M4 - M5 + M7
        Matrix Q2 = add(M3, M5); // M3 + M5
        Matrix Q3 = add(M2, M4); // M2 + M4
        Matrix Q4 = add(add(subtract(M1, M2), M3), M6); // M1 - M2 + M3 + M6

        // combine quadrants
        product = append(append(Q1, Q2, false), append(Q3, Q4, false), true);

        return product;
    }
}
