public class ThomasAlgorithm {
    // Коефіцієнти тридіагональної матриці
    public static double[] a; // Піддіагональ
    public static double[] b; // Головна діагональ
    public static double[] c; // Наддіагональ
    public static double[] d; // Вектор правої частини

    public static double[] method(int n) {
        double[] alpha = new double[n];
        double[] beta = new double[n];
        double[] x = new double[n];

        a = new double[n];
        b = new double[n];
        c = new double[n];
        d = new double[n];

        for (int i = 0; i < n; i++) {
            b[i] = 3;
        }

        for (int i = 0; i < n - 1; i++) {
            a[i] = (i == 0) ? 0 : 1;
        }

        for (int i = 0; i < n - 1; i++) {
            c[i] = (i == n-1) ? 0 : 2;;
        }

        for (int i = 0; i < n; i++) {
            d[i] = i + 1;
        }

        // Прямий хід
        alpha[0] = c[0] / b[0];
        beta[0] = d[0] / b[0];

        for (int i = 1; i < n - 1; i++) {
            double z = b[i] - a[i] * alpha[i - 1];
            alpha[i] = c[i] / z;
            beta[i] = (d[i] - a[i] * beta[i - 1]) / z;
        }
        beta[n - 1] = (d[n - 1] - a[n - 2] * beta[n - 2]) / (b[n - 1] - a[n - 2] * alpha[n - 2]);

        // Зворотний хід
        x[n - 1] = beta[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            x[i] = beta[i] - alpha[i] * x[i + 1];
        }

        return x;
    }
}

