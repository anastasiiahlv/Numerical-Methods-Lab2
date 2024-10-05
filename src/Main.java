import java.util.Scanner;
import static java.lang.Math.*;

public class Main {

    public static void resultOutput(int n) {
        double[][] matrix = Matrix.buildMatrix(n);
        System.out.println("Matrix A: ");
        Matrix.printMatrix(matrix);

        System.out.println();

        double det = Matrix.determinant(matrix);
        System.out.println("Determinant A: " + det);

        System.out.println();

        System.out.println("Inverse matrix  A^(-1): ");
        double[][] invertMatrix = Matrix.invertMatrix(matrix);
        Matrix.printMatrix(invertMatrix);

        System.out.println();

        System.out.println("A norm: " + Matrix.calculateMatrixNorm(matrix));
        System.out.println("A^-1 norm: " + Matrix.calculateMatrixNorm(invertMatrix));

        double conditionNumber = Matrix.calculateConditionNumber(Matrix.calculateMatrixNorm(matrix), Matrix.calculateMatrixNorm(invertMatrix));
        System.out.println("Condition number: " + conditionNumber);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = -1;
        int n = 0;
        double epsilon = 0;

        while(m != 0) {

            System.out.print("Enter a number n for the matrix dimension nxn: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid number: ");
                sc.next();
            }
            n = sc.nextInt();

            System.out.println("Choose the method. Enter 1 or 2.");
            System.out.println("1 - Thomas Algorithm");
            System.out.println("2 - Jacobi Method");
            System.out.print("Enter: ");

            if (sc.hasNextInt()) {
                int selectedMethod = sc.nextInt();
                if (selectedMethod != 1 && selectedMethod != 2) {
                    System.out.println("Invalid selection.");
                    continue;
                }

                switch (selectedMethod) {
                    case 1:
                        double[] solution = ThomasAlgorithm.method(n);

                        System.out.println("Result:");
                        for (double x_i : solution) {
                            System.out.println(x_i);
                        }

                        System.out.println();

                        resultOutput(n);
                        break;
                    case 2:

                        System.out.print("Enter the precision: ");
                        while (!sc.hasNextDouble()) {
                            System.out.println("Invalid input. Please enter a valid number: ");
                            sc.next();
                        }
                        epsilon = sc.nextDouble();

                        double[] initialElements = new double[n];
                        System.out.println("Enter the initial approximation (" + n + " elements): ");
                        for(int i = 0; i < n; i++) {
                            System.out.print("Element [" + (i+1) + "] = " );
                            while (!sc.hasNextDouble()) {
                                System.out.println("Invalid input. Please enter a valid number: ");
                                sc.next();
                            }
                            initialElements[i] = sc.nextDouble();
                        }

                        JacobiMethod.method(epsilon, n, initialElements);

                        resultOutput(n);
                        break;
                }
            } else {
                System.out.print("Invalid input. Please enter a number 1 or 2: ");
                sc.next();
                continue;
            }

            System.out.print("Enter 1 to continue, 0 to exit: ");
            if (sc.hasNextInt()) {
                m = sc.nextInt();
            } else {
                System.out.println("Invalid input. Exiting.");
                break;
            }

        }
        sc.close();
    }
}


