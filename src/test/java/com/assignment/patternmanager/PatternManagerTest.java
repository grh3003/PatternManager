package com.assignment.patternmanager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class PatternManagerTest {
	private static final String TEST_RESOURCE_FOLDER = "src/test/resources/";
	private PatternManager patternManager;

	@BeforeEach
	void setUp() {
		patternManager = new PatternManager();
	}

	@Test
	void testAddPatternCall() {
		patternManager.addPatternCall(1, "pattern1", "path1", true);
		patternManager.addPatternCall(2, "pattern2", "path2", false);

		List<PatternCall> patternCalls = patternManager.getAllPatternCalls();
		assertEquals(2, patternCalls.size());
	}

	@Test
	void testGetPatternCallById() {
		patternManager.addPatternCall(1, "pattern1", "path1", true);
		patternManager.addPatternCall(2, "pattern2", "path2", false);

		PatternCall patternCall = patternManager.getPatternCallById(1);
		assertNotNull(patternCall);
		assertEquals("pattern1", patternCall.getName());
		assertEquals("path1", patternCall.getPatternFile());
		assertTrue(patternCall.isCalled());
	}

	@Test
	void testGetPatternCallsByName() {
		patternManager.addPatternCall(1, "pattern1", "path1", true);
		patternManager.addPatternCall(2, "pattern2", "path2", false);
		patternManager.addPatternCall(3, "pattern1", "path3", true);

		List<PatternCall> patternCalls = patternManager.getPatternCallsByName("pattern1");
		assertEquals(2, patternCalls.size());
	}

	@Test
	void testGetPatternCallsByPath() {
		patternManager.addPatternCall(1, "pattern1", "path1", true);
		patternManager.addPatternCall(2, "pattern2", "path2", false);
		patternManager.addPatternCall(3, "pattern3", "path1", true);

		List<PatternCall> patternCalls = patternManager.getPatternCallsByPath("path1");
		assertEquals(2, patternCalls.size());
	}

	@Test
	void testGetSkippedPatternCalls() {
		patternManager.addPatternCall(1, "pattern1", "path1", true);
		patternManager.addPatternCall(2, "pattern2", "path2", false);
		patternManager.addPatternCall(3, "pattern3", "path3", true);

		List<PatternCall> skippedPatternCalls = patternManager.getSkippedPatternCalls();
		assertEquals(1, skippedPatternCalls.size());
	}

	@Test
	void testGetNotSkippedPatternCalls() {
		patternManager.addPatternCall(1, "pattern1", "path1", true);
		patternManager.addPatternCall(2, "pattern2", "path2", false);
		patternManager.addPatternCall(3, "pattern3", "path3", true);

		List<PatternCall> notSkippedPatternCalls = patternManager.getNotSkippedPatternCalls();
		assertEquals(2, notSkippedPatternCalls.size());
	}

	@Test
	void testGetPatternCallByIdNotFound() {
		patternManager.addPatternCall(1, "pattern1", "path1", true);
		patternManager.addPatternCall(2, "pattern2", "path2", false);

		PatternCall patternCall = patternManager.getPatternCallById(3);
		assertNull(patternCall);
	}

	@Test
	void testGetPatternCallsByNameNotFound() {
		patternManager.addPatternCall(1, "pattern1", "path1", true);
		patternManager.addPatternCall(2, "pattern2", "path2", false);
		patternManager.addPatternCall(3, "pattern3", "path3", true);

		List<PatternCall> patternCalls = patternManager.getPatternCallsByName("pattern4");
		assertTrue(patternCalls.isEmpty());
	}

	@Test
	void testGetPatternCallsByPathNotFound() {
		patternManager.addPatternCall(1, "pattern1", "path1", true);
		patternManager.addPatternCall(2, "pattern2", "path2", false);
		patternManager.addPatternCall(3, "pattern3", "path3", true);

		List<PatternCall> patternCalls = patternManager.getPatternCallsByPath("path4");
		assertTrue(patternCalls.isEmpty());
	}

	@Test
	void testGetSkippedPatternCallsNone() {
		patternManager.addPatternCall(1, "pattern1", "path1", true);
		patternManager.addPatternCall(2, "pattern2", "path2", true);

		List<PatternCall> skippedPatternCalls = patternManager.getSkippedPatternCalls();
		assertTrue(skippedPatternCalls.isEmpty());
	}

	@Test
	void testGetNotSkippedPatternCallsNone() {
		patternManager.addPatternCall(1, "pattern1", "path1", false);
		patternManager.addPatternCall(2, "pattern2", "path2", false);

		List<PatternCall> notSkippedPatternCalls = patternManager.getNotSkippedPatternCalls();
		assertTrue(notSkippedPatternCalls.isEmpty());
	}

	@Test
	void testWriteTuplesToFile() {
		patternManager.addPatternCall(1, "pattern1", "path1", true);
		patternManager.addPatternCall(2, "pattern2", "path2", false);
		patternManager.addPatternCall(3, "pattern3", "path3", true);

		patternManager.storePatternCallsToFile(TEST_RESOURCE_FOLDER + "test_tuples.txt");
		patternManager.readPatternCallsFromFile(TEST_RESOURCE_FOLDER + "test_tuples.txt");

		List<PatternCall> patternCallsFromFile = patternManager.getAllPatternCalls();
		;
		assertEquals(3, patternCallsFromFile.size());
	}

	@Test
	void testReadInitialTuplesEmptyFile() {
		patternManager.readPatternCallsFromFile(TEST_RESOURCE_FOLDER + "empty_file.txt");
		List<PatternCall> patternCalls = patternManager.getAllPatternCalls();
		assertTrue(patternCalls.isEmpty());
	}

	@Test
	void testReadInitialTuplesMultiplePatternCalls() {
		patternManager.readPatternCallsFromFile(TEST_RESOURCE_FOLDER + "multiple_patterns.txt");
		List<PatternCall> patternCalls = patternManager.getAllPatternCalls();
		assertEquals(3, patternCalls.size());
		assertEquals(new PatternCall(1, "pattern1", "path1", true), patternCalls.get(0));
		assertEquals(new PatternCall(2, "pattern2", "path2", false), patternCalls.get(1));
		assertEquals(new PatternCall(3, "pattern3", "path3", true), patternCalls.get(2));
	}

	@Test
	void testReadInitialTuplesLargeFile() {
		// Create a large file with 1000 pattern calls
		createLargeFile(TEST_RESOURCE_FOLDER + "large_file.txt", 1000);

		patternManager.readPatternCallsFromFile(TEST_RESOURCE_FOLDER + "large_file.txt");
		List<PatternCall> patternCalls = patternManager.getAllPatternCalls();
		assertEquals(1000, patternCalls.size());
	}

	@Test
	void testReadInitialTuplesInvalidFormat() {
		// Create a file with invalid format
		createInvalidFormatFile(TEST_RESOURCE_FOLDER + "invalid_format.txt");

		patternManager.readPatternCallsFromFile(TEST_RESOURCE_FOLDER + "invalid_format.txt");
		List<PatternCall> patternCalls = patternManager.getAllPatternCalls();
		assertTrue(patternCalls.isEmpty());
	}

	@Test
	void testReadInitialTuplesInvalidOrder() {
		// Create a file with invalid order of pattern call fields
		createInvalidOrderFile(TEST_RESOURCE_FOLDER + "invalid_order.txt");

		patternManager.readPatternCallsFromFile(TEST_RESOURCE_FOLDER + "invalid_order.txt");
		List<PatternCall> patternCalls = patternManager.getAllPatternCalls();
		// only 2 are valid patterns
		// pattern1, path1, true, 1
		// 2, pattern2, path2, false
		// 3, pattern3, path3, true
		assertEquals(2, patternCalls.size());
	}

	private void createInvalidOrderFile(String fileName) {
		try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
			writer.println("pattern1, path1, true, 1"); // Invalid order
			writer.println("2, pattern2, path2, false");
			writer.println("3, pattern3, path3, true");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void createLargeFile(String fileName, int numPatternCalls) {
		try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
			for (int i = 1; i <= numPatternCalls; i++) {
				writer.println(i + ", pattern" + i + ", path" + i + ", true");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void createInvalidFormatFile(String fileName) {
		try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
			writer.println("1, pattern1, path1"); // Missing 'called' flag
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
