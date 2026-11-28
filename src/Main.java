package src;

class Main {
    public static void main(String[] args) {
        Matrix one = new Matrix(new int[][]{{3, 4, 8}, {2, 6, 7}, {1, 8, 9}});
        Matrix two = new Matrix(new int[][]{{9, 0, 5}, {21, 2, 3}, {7, 1, 2}});

        MatrixOperator op = new MatrixOperator();

        Matrix product = op.multiply(one, two);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(product.getValue(j, i) + " ");
            }
            System.out.println();
        }
        System.out.println();

        Matrix a = op.split(one, true, 1)[0];
        for (int i = 0; i < a.getLength(); i++) {
            for (int j = 0; j < a.getWidth(); j++) {
                System.out.print(a.getValue(j, i) + " ");
            }
            System.out.println();
        }

        Matrix b = op.split(one, true, 1)[1];
        for (int i = 0; i < b.getLength(); i++) {
            for (int j = 0; j < b.getWidth(); j++) {
                System.out.print(b.getValue(j, i) + " ");
            }
            System.out.println();
        }
    }
}