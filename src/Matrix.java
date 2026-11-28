package src;

public class Matrix {
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

    public int getLength() { return length; }

    public int getWidth() { return width; }

    public int getValue(int x, int y) {
        return matrix[y][x]; // 2D array elements are ordered by y-coordinate first
    }

    public void setValue(int x, int y, int val) { matrix[y][x] = val; }
}