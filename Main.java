class Main {
    public static void main(String[] args) {
        Matrix one = new Matrix(new int[][]{{3, 4, 8}, {2, 6, 7}, {1, 8, 9}});
        Matrix two = new Matrix(new int[][]{{9, 0, 5}, {21, 2, 3}, {7, 1, 2}});

        Matrix product = one.multiply(two);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(product.getValue(j, i) + " ");
            }
            System.out.println();
        }
    }
}