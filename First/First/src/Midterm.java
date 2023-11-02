import java.util.Scanner;

public class Midterm {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the value of x: ");
        double x = scanner.nextDouble();

        System.out.print("Enter the value of n: ");
        int n = scanner.nextInt();

        double sum = calculateSeries(x, n);

        System.out.println("The sum of the series S = " + sum);
    }

    public static double calculateSeries(double x, int n) {
        double sum = 0.0;
        double term = 1.0;

        for (int i = 2; i <= n; i += 2) {
            term *= (x * x) / (i * (i - 1));
            sum += term;
        }

        return sum;
    }
}
