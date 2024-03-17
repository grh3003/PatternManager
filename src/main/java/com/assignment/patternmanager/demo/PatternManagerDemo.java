package com.assignment.patternmanager.demo;

import java.io.*;
import java.util.List;
import java.util.Scanner;

import com.assignment.patternmanager.PatternCall;
import com.assignment.patternmanager.PatternManager;

/**
 * A demo class to showcase the functionalities of the PatternManager.
 */
public class PatternManagerDemo {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Main method to run the Pattern Manager demo.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        PatternManager patternManager = new PatternManager();

        // Read initial tuples from a provided file path
        readInitialTuples(patternManager, "initial_tuples.txt");

        // Add additional tuples with dummy data
        patternManager.addPatternCall(100, "dummyPattern1", "src/patterns/Dummy1.pat", false);
        patternManager.addPatternCall(101, "dummyPattern2", "src/patterns/Dummy2.pat", true);

        // Display menu and interact with user
        displayMenu(patternManager);

        // Write the tuples back to a different file
        writeTuplesToFile(patternManager, "updated_tuples.txt");
    }

    /**
     * Reads initial pattern call tuples from a file and adds them to the pattern manager.
     *
     * @param patternManager The pattern manager to add pattern calls to.
     * @param filePath       The file path to read tuples from.
     */
    private static void readInitialTuples(PatternManager patternManager, String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    String path = parts[2].trim();
                    boolean called = Boolean.parseBoolean(parts[3].trim());
                    patternManager.addPatternCall(id, name, path, called);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading initial tuples from file: " + e.getMessage());
        }
    }

    /**
     * Displays the menu options and interacts with the user.
     *
     * @param patternManager The pattern manager to perform operations on.
     */
    private static void displayMenu(PatternManager patternManager) {
        System.out.println("Pattern Manager Demo");
        System.out.println("1. Retrieve pattern call by ID");
        System.out.println("2. List pattern calls by name");
        System.out.println("3. List pattern calls by path");
        System.out.println("4. List skipped pattern calls");
        System.out.println("5. List not skipped pattern calls");
        System.out.println("6. Exit");

        boolean exit = false;
        while (!exit) {
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    retrievePatternCallById(patternManager);
                    break;
                case 2:
                    listPatternCallsByName(patternManager);
                    break;
                case 3:
                    listPatternCallsByPath(patternManager);
                    break;
                case 4:
                    listSkippedPatternCalls(patternManager);
                    break;
                case 5:
                    listNotSkippedPatternCalls(patternManager);
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }
    }

    /**
     * Retrieves a pattern call by ID and prints it to the console.
     *
     * @param patternManager The pattern manager to retrieve pattern calls from.
     */
    private static void retrievePatternCallById(PatternManager patternManager) {
        System.out.print("Enter pattern ID: ");
        int id = scanner.nextInt();
        PatternCall patternCall = patternManager.getPatternCallById(id);
        if (patternCall != null) {
            System.out.println("Pattern call found:");
            System.out.println(patternCall);
        } else {
            System.out.println("Pattern call with ID " + id + " not found.");
        }
    }

    /**
     * Lists pattern calls by name and prints them to the console.
     *
     * @param patternManager The pattern manager to list pattern calls from.
     */
    private static void listPatternCallsByName(PatternManager patternManager) {
        System.out.print("Enter pattern name: ");
        String name = scanner.nextLine();
        List<PatternCall> patternCalls = patternManager.getPatternCallsByName(name);
        if (!patternCalls.isEmpty()) {
            System.out.println("Pattern calls with name '" + name + "':");
            patternCalls.forEach(System.out::println);
        } else {
            System.out.println("No pattern calls found with name '" + name + "'.");
        }
    }

    /**
     * Lists pattern calls by path and prints them to the console.
     *
     * @param patternManager The pattern manager to list pattern calls from.
     */
    private static void listPatternCallsByPath(PatternManager patternManager) {
        System.out.print("Enter pattern path: ");
        String path = scanner.nextLine();
        List<PatternCall> patternCalls = patternManager.getPatternCallsByPath(path);
        if (!patternCalls.isEmpty()) {
            System.out.println("Pattern calls with path '" + path + "':");
            patternCalls.forEach(System.out::println);
        } else {
            System.out.println("No pattern calls found with path '" + path + "'.");
        }
    }

    /**
     * Lists skipped pattern calls and prints them to the console.
     *
     * @param patternManager The pattern manager to list pattern calls from.
     */
    private static void listSkippedPatternCalls(PatternManager patternManager) {
        List<PatternCall> patternCalls = patternManager.getSkippedPatternCalls();
        if (!patternCalls.isEmpty()) {
            System.out.println("Skipped pattern calls:");
            patternCalls.forEach(System.out::println);
        } else {
            System.out.println("No skipped pattern calls found.");
        }
    }

    /**
     * Lists not skipped pattern calls and prints them to the console.
     *
     * @param patternManager The pattern manager to list pattern calls from.
     */
    private static void listNotSkippedPatternCalls(PatternManager patternManager) {
        List<PatternCall> patternCalls = patternManager.getNotSkippedPatternCalls();
        if (!patternCalls.isEmpty()) {
            System.out.println("Not skipped pattern calls:");
            patternCalls.forEach(System.out::println);
        } else {
            System.out.println("No not skipped pattern calls found.");
        }
    }

    /**
     * Writes the pattern call tuples from the pattern manager to a file.
     *
     * @param patternManager The pattern manager containing pattern calls to write.
     * @param filePath       The file path to write the tuples to.
     */
    private static void writeTuplesToFile(PatternManager patternManager, String filePath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            List<PatternCall> patternCalls = patternManager.getAllPatternCalls();
            for (PatternCall patternCall : patternCalls) {
                writer.println(patternCall.getId() + ", " + patternCall.getName() + ", "
                        + patternCall.getPatternFile() + ", " + patternCall.isCalled());
            }
        } catch (IOException e) {
            System.err.println("Error writing pattern calls to file: " + e.getMessage());
        }
    }
}
