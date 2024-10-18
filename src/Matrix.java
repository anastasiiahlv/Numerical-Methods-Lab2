public class Matrix {

    public static double[][] buildMatrix(int n) {
        double[][] matrix = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    matrix[i][j] = 3; // Головна діагональ
                } else if (j == i + 1) {
                    matrix[i][j] = 2; // Перша діагональ праворуч
                } else if (j == i - 1) {
                    matrix[i][j] = 1; // Перша діагональ ліворуч
                } else {
                    matrix[i][j] = 0; // Інші елементи
                }
            }
        }

        return matrix;
    }

    public static void printMatrix(double[][] matrix) {
        for (double[] row : matrix) {
            System.out.print("| ");
            for (double value : row) {
                System.out.printf("%6.2f ", value);
            }
            System.out.print(" |");
            System.out.println();
        }
    }

    public static double calculateConditionNumber(double a, double b) {
        return a*b;
    }

    public static double calculateMatrixNorm(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        double maxColumnSum = 0;

        for (int j = 0; j < cols; j++) {
            double columnSum = 0;

            for (int i = 0; i < rows; i++) {
                columnSum += Math.abs(matrix[i][j]);
            }

            maxColumnSum = Math.max(maxColumnSum, columnSum);
        }

        return maxColumnSum;
    }
}
