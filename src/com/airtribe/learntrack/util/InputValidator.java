package com.airtribe.learntrack.util;

import java.util.Scanner;

/**
 * Utility class providing safe input reading from Scanner to prevent console crashes.
 */
public class InputValidator {

    private InputValidator() {
    }

    /**
     * Reads a valid integer from the scanner. Prompts continuously until a valid integer is provided.
     */
    public static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input! Please enter a valid integer number.");
            }
        }
    }

    /**
     * Reads a non-empty string from the scanner.
     */
    public static String readNonEmptyString(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("❌ Input cannot be empty! Please try again.");
        }
    }

    /**
     * Reads a boolean choice (Y/N or True/False).
     */
    public static boolean readBoolean(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt + " (Y/N): ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y") || input.equals("yes") || input.equals("true")) {
                return true;
            } else if (input.equals("n") || input.equals("no") || input.equals("false")) {
                return false;
            }
            System.out.println("❌ Please enter Y (Yes) or N (No).");
        }
    }
}
