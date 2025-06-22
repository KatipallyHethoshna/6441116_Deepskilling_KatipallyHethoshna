import java.util.Scanner;

public class FinancialForecast {

    // Recursive method to calculate future value
    public static double calculateFutureValue(double presentValue, double growthRate, int years) {
        // Base case
        if (years == 0) {
            return presentValue;
        }
        // Recursive step
        return calculateFutureValue(presentValue * (1 + growthRate), growthRate, years - 1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Take input from user
        System.out.print("Enter present value (₹): ");
        double presentValue = scanner.nextDouble();

        System.out.print("Enter annual growth rate (as a percentage, e.g., 8 for 8%): ");
        double growthRatePercent = scanner.nextDouble();
        double growthRate = growthRatePercent / 100.0;

        System.out.print("Enter number of years: ");
        int years = scanner.nextInt();

        // Calculate future value
        double futureValue = calculateFutureValue(presentValue, growthRate, years);

        // Output result
        System.out.printf("Future value after %d years: ₹%.2f%n", years, futureValue);

        scanner.close();
    }
}
