package utils;

import java.util.Scanner;

public class InputValidator {
    public static String getNonEmptyString(Scanner sc, String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = sc.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Error: Input cannot be empty. Please try again.");
            }
        } while (input.isEmpty());
        return input;
    }
    
    public static double getValidAccuracy(Scanner sc, String prompt) {
        double accuracy = -1;
        do {
            System.out.print(prompt);
            try {
                accuracy = Double.parseDouble(sc.nextLine().trim());
                if (accuracy < 0 || accuracy > 100) {
                    System.out.println("Error: Accuracy must be between 0 and 100.");
                    accuracy = -1;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid numeric input.");
            }
        } while (accuracy < 0 || accuracy > 100);
        return accuracy;
    }
}
