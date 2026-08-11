import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Set locale to US to properly read floating-point numbers with standard decimal point (.)
        Locale.setDefault(Locale.US);
        
        Scanner scanner = new Scanner(System.in);

        int employeeNumber = scanner.nextInt();
        int workedHours = scanner.nextInt();
        double hourlyRate = scanner.nextDouble();

        double salary = workedHours * hourlyRate;

        System.out.printf("NUMBER = %d\n", employeeNumber);
        System.out.printf("SALARY = U$ %.2f\n", salary);

        scanner.close();
    }
}
