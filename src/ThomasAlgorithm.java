public class ThomasAlgorithm {

    public static double determinant;

    public static double[] method(int n, double[] b) {
        double[] a = new double[n]; // Піддіагональ
        double[] c = new double[n]; // Наддіагональ
        double[] d = new double[n]; // Вектор правої частини
        double[] alpha = new double[n];
        double[] beta = new double[n];
        double[] x = new double[n];

        // Заповнюємо коефіцієнти тридіагональної матриці
        for (int i = 0; i < n; i++) {
            d[i] = b[i]; // Вектор правої частини
            if (i > 0) {
                a[i] = 1; // Піддіагональ
            } else {
                a[i] = 0; // Перший елемент піддіагоналі
            }

            c[i] = (i < n - 1) ? 2 : 0; // Наддіагональ
        }

        determinant = 3; // Початкове значення визначника

        // Прямий хід
        alpha[0] = c[0] / 3;
        beta[0] = d[0] / 3;

        for (int i = 1; i < n - 1; i++) {
            double z = 3 - a[i] * alpha[i - 1];
            alpha[i] = c[i] / z;
            beta[i] = (d[i] - a[i] * beta[i - 1]) / z;
            determinant *= z; // Множимо визначник на z
        }
        determinant *= (3 - a[n - 2] * alpha[n - 2]); // Останній множник для визначника

        beta[n - 1] = (d[n - 1] - a[n - 2] * beta[n - 2]) / (3 - a[n - 2] * alpha[n - 2]);

        // Зворотний хід
        x[n - 1] = beta[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            x[i] = beta[i] - alpha[i] * x[i + 1];
        }

        return x;
    }

    public static double[][] inverseMatrix(int n) {
        double[][] inverseMatrix = new double[n][n];

        // Для кожного стовпця одиничної матриці
        for (int k = 0; k < n; k++) {
            double[] e = new double[n];  // Одиничний вектор
            e[k] = 1.0;  // У цьому стовпці одиничної матриці на позиції k ставимо 1

            // Викликаємо метод прогонки для системи A * x_k = e_k
            double[] column = method(n, e);  // Рішення системи

            // Записуємо результат в обернену матрицю
            for (int i = 0; i < n; i++) {
                inverseMatrix[i][k] = column[i];
            }
        }

        return inverseMatrix;
    }

}