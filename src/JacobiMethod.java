import java.util.Scanner;

public class JacobiMethod {

    public static void method(double epsilon, int n, double[] initialElements) {
        Vector x_k = new Vector(initialElements);

        Vector x_k_next;
        Vector vectorSubtraction;
        Vector result = new Vector(new double[n]);

        double norm;
        int iterationCount = 0;
        boolean is_result = false;

        for (int i = 1; i <= 100; i++) {
            x_k_next = Vector.nextIterationVector(x_k);
            vectorSubtraction = Vector.vectorSubtraction(x_k, x_k_next);
            norm = Vector.vectorNorm(vectorSubtraction);

            System.out.println(" ----------------------------------------------------------");
            System.out.println(i + " iteration");
            System.out.print("x_k: ");
            Vector.printVector(x_k_next);
            System.out.print("x_k_prev: ");
            Vector.printVector(x_k);
            System.out.println();

            System.out.println("x_k_next - x_k");
            Vector.printVector(vectorSubtraction);
            System.out.println();

            System.out.println("Vector norm: " + norm);
            System.out.println(" ----------------------------------------------------------");

            x_k = x_k_next;

            if (norm <= epsilon && !is_result) {
                iterationCount = i;
                is_result = true;
                result = x_k_next;
                break;
            }
        }

        if (is_result) {
            System.out.println("The solution of the system with an epsilon of " + epsilon + " is:");
            Vector.printVector(result);
            System.out.println("Result is found during " + iterationCount + " iteration");
        } else {
            System.out.println("Solution is not found within 100 iterations.");
        }
    }
}

