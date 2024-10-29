package edu.ncsu.csc216.pack_scheduler.directory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.Before;
import org.junit.jupiter.api.Test;

/**
 * Tests StudentDirectory file.
 * 
 * @author Sarah Heckman
 * @author Gabe Frain
 * @author Justin Pak
 * @author Bryce Yang
 */
public class StudentDirectoryTest {

	/** Valid course records */
	private final String validTestFile = "test-files/student_records.txt";
	/** Valid course records */
	private final String invalidTestFile = "test-files/invalid_records.txt";
	/** Test first name */
	private static final String FIRST_NAME = "Stu";
	/** Test last name */
	private static final String LAST_NAME = "Dent";
	/** Test id */
	private static final String ID = "sdent";
	/** Test email */
	private static final String EMAIL = "sdent@ncsu.edu";
	/** Test password */
	private static final String PASSWORD = "pw";
	/** Test different password */
	private static final String DIFPASSWORD = "dpw";
	/** Test null password */
	private static final String PASSWORDNULL = null;
	/** Test empty password */
	private static final String PASSWORDEMPTY = "";
	/** Test max credits */
	private static final int MAX_CREDITS = 15;
	/** Test second first name */
	private static final String FIRST_NAME2 = "Tea";
	/** Test second last name */
	private static final String LAST_NAME2 = "Cher";
	/** Test second id */
	private static final String ID2 = "tcher";
	/** Test second email */
	private static final String EMAIL2 = "tcher@ncsu.edu";
	/** Test second password */
	private static final String PASSWORD2 = "wp";
	/** Test lower than max credits */
	private static final int MAX_CREDITSL = 2;
	/** Test third first name */
	private static final String FIRST_NAME3 = "Joe";
	/** Test third last name */
	private static final String LAST_NAME3 = "Man";
	/** Test third id */
	private static final String ID3 = "jman";
	/** Test third email */
	private static final String EMAIL3 = "jman@ncsu.edu";
	/** Test third password */
	private static final String PASSWORD3 = "pass";
	/** Test more than max credits */
	private static final int MAX_CREDITSM = 24;
	/** Test fourth first name */
	private static final String FIRST_NAME4 = "Slim";
	/** Test fourth last name */
	private static final String LAST_NAME4 = "Jim";
	/** Test fourth id */
	private static final String ID4 = "sjim";
	/** Test fourth email */
	private static final String EMAIL4 = "sjim@ncsu.edu";
	/** Test fourth password */
	private static final String PASSWORD4 = "word";

	/**
	 * Resets course_records.txt for use in other tests.
	 * 
	 * @throws Exception if something fails during setup.
	 */
	@Before
	public void setUp() throws Exception {
		// Reset student_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "expected_full_student_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "student_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}

	/**
	 * Tests StudentDirectory().
	 */
	@Test
	public void testStudentDirectory() {
		// Test that the StudentDirectory is initialized to an empty list
		StudentDirectory sd = new StudentDirectory();
		assertFalse(sd.removeStudent("sesmith5"));
		assertEquals(0, sd.getStudentDirectory().length);
	}

	/**
	 * Tests StudentDirectory.testNewStudentDirectory().
	 */
	@Test
	public void testNewStudentDirectory() {
		// Test that if there are students in the directory, they
		// are removed after calling newStudentDirectory().
		StudentDirectory sd = new StudentDirectory();

		sd.loadStudentsFromFile(validTestFile);
		assertEquals(10, sd.getStudentDirectory().length);

		sd.newStudentDirectory();
		assertEquals(0, sd.getStudentDirectory().length);
	}

	/**
	 * Tests StudentDirectory.loadStudentsFromFile().
	 */
	@Test
	public void testLoadStudentsFromFile() {
		StudentDirectory sd = new StudentDirectory();

		// Test valid file
		sd.loadStudentsFromFile(validTestFile);
		assertEquals(10, sd.getStudentDirectory().length);

		// Test invalid file
		Exception e = assertThrows(IllegalArgumentException.class, () -> sd.loadStudentsFromFile(invalidTestFile));
		assertEquals("Unable to read file " + invalidTestFile, e.getMessage());
	}

	/**
	 * Tests StudentDirectory.addStudent().
	 */
	@Test
	public void testAddStudent() {
		StudentDirectory sd = new StudentDirectory();

		// Test valid Student
		sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS);
		String[][] studentDirectory = sd.getStudentDirectory();
		assertEquals(1, studentDirectory.length);
		assertEquals(FIRST_NAME, studentDirectory[0][0]);
		assertEquals(LAST_NAME, studentDirectory[0][1]);
		assertEquals(ID, studentDirectory[0][2]);

		// Test null password
		Exception pn = assertThrows(IllegalArgumentException.class,
				() -> sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORDNULL, PASSWORD, MAX_CREDITS));
		assertEquals("Invalid password", pn.getMessage());

		// Test null repeat password
		Exception rpn = assertThrows(IllegalArgumentException.class,
				() -> sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORDNULL, MAX_CREDITS));
		assertEquals("Invalid password", rpn.getMessage());

		// Test empty password
		Exception pe = assertThrows(IllegalArgumentException.class,
				() -> sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORDEMPTY, PASSWORD, MAX_CREDITS));
		assertEquals("Invalid password", pe.getMessage());

		// Test null empty password
		Exception rpe = assertThrows(IllegalArgumentException.class,
				() -> sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORDEMPTY, MAX_CREDITS));
		assertEquals("Invalid password", rpe.getMessage());

		// Test different passwords
		Exception dp = assertThrows(IllegalArgumentException.class,
				() -> sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, DIFPASSWORD, MAX_CREDITS));
		assertEquals("Passwords do not match", dp.getMessage());

		// Add duplicate student id
		assertTrue(sd.addStudent(FIRST_NAME2, LAST_NAME2, ID2, EMAIL2, PASSWORD2, PASSWORD2, MAX_CREDITS));
		assertFalse(sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS));

		// Add student with too little credits
		assertTrue(sd.addStudent(FIRST_NAME3, LAST_NAME3, ID3, EMAIL3, PASSWORD3, PASSWORD3, MAX_CREDITSL));

		// Add student with too many credits
		assertTrue(sd.addStudent(FIRST_NAME4, LAST_NAME4, ID4, EMAIL4, PASSWORD4, PASSWORD4, MAX_CREDITSM));

	}

	/**
	 * Tests StudentDirectory.removeStudent()
	 */
	@Test
	public void testRemoveStudent() {
		StudentDirectory sd = new StudentDirectory();

		// Add students and remove
		sd.loadStudentsFromFile(validTestFile);
		assertEquals(10, sd.getStudentDirectory().length);
		assertTrue(sd.removeStudent("efrost"));
		String[][] studentDirectory = sd.getStudentDirectory();
		assertEquals(9, studentDirectory.length);
		//updated remove test to change from 5th index to 1st index
		assertEquals("Lane", studentDirectory[1][0]);
		assertEquals("Berg", studentDirectory[1][1]);
		assertEquals("lberg", studentDirectory[1][2]);
	}

	/**
	 * Tests StudentDirectory.saveStudentDirectory().
	 */
	@Test
	public void testSaveStudentDirectory() {
		StudentDirectory sd = new StudentDirectory();

		// Add a student
		sd.addStudent("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", "pw", "pw", 15);
		assertEquals(1, sd.getStudentDirectory().length);

		// Valid File
		sd.saveStudentDirectory("test-files/actual_student_records.txt");
		checkFiles("test-files/expected_student_records1.txt", "test-files/actual_student_records.txt");

	}

	/**
	 * Helper method to compare two files for the same contents
	 * 
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new FileInputStream(expFile));
			Scanner actScanner = new Scanner(new FileInputStream(actFile));

			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}

			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}

}