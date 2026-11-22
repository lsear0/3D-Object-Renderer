class Matrix {
    private int length;
    private int width;
    private int[][] matrix;

    public Matrix(int[][] matrix) {
        this.length = matrix.length;
        this.width = matrix[0].length;
        this.matrix = matrix;
    }

    public Matrix(int length, int width) {
        this.length = length;
        this.width = width;

        matrix = new int[length][width];
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getValue(int x, int y) {
        return matrix[y][x]; // 2D array elements are ordered by y-coordinate first
    }

    public void setValue(int x, int y, int val) {
        matrix[y][x] = val;
    }

    public Matrix multiply(Matrix other) {
        // reject invalid multiplications
        if (width != other.getLength()) {
            return null;
        }

        // the product of two matrices with dimensions m x n and n x k has dimensions m x k
        Matrix product = new Matrix(length, other.getWidth());

        // traditional matrix multiplication algorithm
        // TODO: implement Strassen's algorithm
        for (int i = 0; i < product.getLength(); i++) {
            for (int j = 0; j < product.getWidth(); j++) {
                int val = 0;
                for (int k = 0; k < width; k++) {
                    val += getValue(k, i) * other.getValue(j, k);
                }
                product.setValue(j, i, val);
            }
        }

        return product;
    }
}