package com.assignment.patternmanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Manages a collection of PatternCall objects and provides methods to perform
 * operations on them.
 */
public class PatternManager {
	private List<PatternCall> patternCalls;

	/**
	 * Constructor for creating a PatternManager object.
	 */
	public PatternManager() {
		patternCalls = new ArrayList<>();
	}

	/**
	 * Adds a new pattern call to the collection.
	 *
	 * @param id          The unique identifier of the pattern call.
	 * @param name        The user-defined name of the pattern call.
	 * @param patternFile The project-relative path to the pattern file.
	 * @param called      A boolean flag indicating whether the pattern is called or
	 *                    not.
	 */
	public void addPatternCall(int id, String name, String patternFile, boolean called) {
		patternCalls.add(new PatternCall(id, name, patternFile, called));
	}

	/**
	 * Retrieves a pattern call by its identifier.
	 *
	 * @param id The unique identifier of the pattern call to retrieve.
	 * @return The PatternCall object with the specified identifier, or null if not
	 *         found.
	 */
	public PatternCall getPatternCallById(int id) {
		for (PatternCall patternCall : patternCalls) {
			if (patternCall.getId() == id) {
				return patternCall;
			}
		}
		return null; // If not found
	}

	/**
	 * Retrieves all pattern calls with a specified name.
	 *
	 * @param name The name of the pattern calls to retrieve.
	 * @return A list of PatternCall objects with the specified name.
	 */
	public List<PatternCall> getPatternCallsByName(String name) {
		List<PatternCall> result = new ArrayList<>();
		for (PatternCall patternCall : patternCalls) {
			if (patternCall.getName().equals(name)) {
				result.add(patternCall);
			}
		}
		return result;
	}

	/**
	 * Retrieves all pattern calls with a specified path.
	 *
	 * @param path The path of the pattern calls to retrieve.
	 * @return A list of PatternCall objects with the specified path.
	 */
	public List<PatternCall> getPatternCallsByPath(String path) {
		List<PatternCall> result = new ArrayList<>();
		for (PatternCall patternCall : patternCalls) {
			if (patternCall.getPatternFile().equals(path)) {
				result.add(patternCall);
			}
		}
		return result;
	}

	/**
	 * Retrieves all pattern calls that are skipped (i.e., not called).
	 *
	 * @return A list of PatternCall objects that are skipped.
	 */
	public List<PatternCall> getSkippedPatternCalls() {
		List<PatternCall> result = new ArrayList<>();
		for (PatternCall patternCall : patternCalls) {
			if (!patternCall.isCalled()) {
				result.add(patternCall);
			}
		}
		return result;
	}

	/**
	 * Retrieves all pattern calls that are not skipped (i.e., called).
	 *
	 * @return A list of PatternCall objects that are not skipped.
	 */
	public List<PatternCall> getNotSkippedPatternCalls() {
		List<PatternCall> result = new ArrayList<>();
		for (PatternCall patternCall : patternCalls) {
			if (patternCall.isCalled()) {
				result.add(patternCall);
			}
		}
		return result;
	}

	/**
	 * Stores the pattern calls to a file.
	 *
	 * @param filePath The path of the file to store the pattern calls.
	 */
	public void storePatternCallsToFile(String filePath) {
		try (PrintWriter writer = new PrintWriter(filePath)) {
			for (PatternCall patternCall : patternCalls) {
				writer.println(patternCall.getId() + ", " + patternCall.getName() + ", " + patternCall.getPatternFile()
						+ ", " + patternCall.isCalled());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Reads the pattern calls from a file and populates the collection.
	 *
	 * @param filePath The path of the file to read the pattern calls from.
	 * @return
	 */
	public void readPatternCallsFromFile(String filePath) {
		patternCalls.clear(); // Clear existing data
		try (Scanner scanner = new Scanner(new File(filePath))) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] parts = line.split(", ");
				if (parts.length == 4) {
					try {
						int id = Integer.parseInt(parts[0].trim());
						String name = parts[1].trim();
						String patternFile = parts[2].trim();
						boolean called = Boolean.parseBoolean(parts[3].trim());
						patternCalls.add(new PatternCall(id, name, patternFile, called));
					} catch (NumberFormatException e) {
			               System.err.println("Skipping line due to invalid pattern: " + line);

					}
				}else {
                    // Skip line if format is invalid
                    System.err.println("Skipping line due to invalid format: " + line);
                }
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Returns list of all the pattern calls
	 * 
	 * @return
	 */
	public List<PatternCall> getAllPatternCalls() {

		return patternCalls;
	}
}