import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputHelper {

    // Scanner for taking user input
    private static Scanner scanner = new Scanner(System.in);

    // Read an integer
    public static int readInt(String message) {

        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine());

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    // Read a positive integer
    public static int readPositiveInt(String message) {

        while (true) {
            int number = readInt(message);

            if (number > 0) {
                return number;
            }

            System.out.println("Please enter a positive number.");
        }
    }

    // Read a double value
    public static double readDouble(String message) {

        while (true) {
            try {
                System.out.print(message);
                return Double.parseDouble(scanner.nextLine());

            } catch (NumberFormatException e) {
                System.out.println(
                    "Invalid input. Please enter a valid number."
                );
            }
        }
    }

    // Read a positive double
    public static double readPositiveDouble(String message) {

        while (true) {
            double number = readDouble(message);

            if (number > 0) {
                return number;
            }

            System.out.println("Please enter a value greater than 0.");
        }
    }

    // Read a non-negative double
    public static double readNonNegativeDouble(String message) {

        while (true) {
            double number = readDouble(message);

            if (number >= 0) {
                return number;
            }

            System.out.println("Value cannot be negative.");
        }
    }

    // Read a required string
    public static String readRequiredString(String message) {

        while (true) {
            System.out.print(message);

            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println("This field cannot be empty.");
        }
    }

    // Read a date in YYYY-MM-DD format
    public static String readDate(String message) {

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd");

        while (true) {

            System.out.print(message);

            String date = scanner.nextLine().trim();

            try {
                LocalDate.parse(date, formatter);
                return date;

            } catch (DateTimeParseException e) {
                System.out.println(
                    "Invalid date. Use format YYYY-MM-DD."
                );
            }
        }
    }

    // Read Yes or No
    public static boolean readYesNo(String message) {

        while (true) {

            System.out.print(message);

            String input =
                    scanner.nextLine().trim().toLowerCase();

            if (input.equals("yes") || input.equals("y")) {
                return true;
            }

            if (input.equals("no") || input.equals("n")) {
                return false;
            }

            System.out.println(
                "Please enter yes/y or no/n."
            );
        }
    }

    // Pause the program
    public static void pause() {

        System.out.println();
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }
}