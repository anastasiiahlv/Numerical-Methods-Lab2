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

    public static double[][] invertMatrix(double[][] matrix) {
        int n = matrix.length;
        double[][] extendedMatrix = new double[n][2 * n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                extendedMatrix[i][j] = matrix[i][j];
            }
            extendedMatrix[i][i + n] = 1;
        }

        for (int i = 0; i < n; i++) {
            // Знаходимо провідний елемент
            double conductiveElement = extendedMatrix[i][i];
            for (int j = 0; j < 2 * n; j++) {
                extendedMatrix[i][j] /= conductiveElement;
            }

            // Зануляємо елементи стовпця
            for (int j = 0; j < n; j++) {
                if (j != i) {
                    double factor = extendedMatrix[j][i];
                    for (int k = 0; k < 2 * n; k++) {
                        extendedMatrix[j][k] -= factor * extendedMatrix[i][k];
                    }
                }
            }
        }

        // Виділення оберненої матриці
        double[][] inverseMatrix = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                inverseMatrix[i][j] = extendedMatrix[i][j + n];
            }
        }

        return inverseMatrix;
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

    public static double determinant(double[][] matrix) {
        int n = matrix.length;

        if (matrix[0].length != n) {
            throw new IllegalArgumentException("Матриця повинна бути квадратною");
        }

        if (n == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }

        double det = 0;

        for (int i = 0; i < n; i++) {
            double[][] subMatrix = createSubMatrix(matrix, 0, i);
            det += Math.pow(-1, i) * matrix[0][i] * determinant(subMatrix);
        }

        return det;
    }

    private static double[][] createSubMatrix(double[][] matrix, int excludingRow, int excludingCol) {
        int n = matrix.length;
        double[][] subMatrix = new double[n - 1][n - 1];
        int row = -1;

        for (int i = 0; i < n; i++) {
            if (i == excludingRow) {
                continue;
            }
            row++;
            int col = -1;
            for (int j = 0; j < n; j++) {
                if (j == excludingCol) {
                    continue;
                }
                col++;
                subMatrix[row][col] = matrix[i][j];
            }
        }

        return subMatrix;
    }
}
