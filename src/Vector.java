import java.util.Arrays;

public class Vector {
    double[] elements;

    public Vector(double[] elements) {
        this.elements = elements;
    }

    public static Vector nextIterationVector(Vector x_k) {
        double[] newElements = new double[x_k.elements.length];

        for (int i = 0; i < x_k.elements.length; i++) {
            double left = (i > 0) ? -1 * x_k.elements[i - 1] : 0;
            double right = (i < x_k.elements.length - 1) ? -2 * x_k.elements[i + 1] : 0;
            newElements[i] = 0.33 * (left + right + (i + 1));
        }

        return new Vector(newElements);
    }

    public static Vector vectorSubtraction(Vector x_k, Vector x_k_next) {
        double[] result = new double[x_k.elements.length];
        for (int i = 0; i < x_k.elements.length; i++) {
            result[i] = x_k_next.elements[i] - x_k.elements[i];
        }
        return new Vector(result);
    }

    public static double vectorNorm(Vector x) {
        double max = Math.abs(x.elements[0]);
        for (int i = 1; i < x.elements.length; i++) {
            if (Math.abs(x.elements[i]) > max) {
                max = Math.abs(x.elements[i]);
            }
        }
        return max;
    }

    public static void printVector(Vector x) {
        System.out.println(Arrays.toString(x.elements));
    }
}
