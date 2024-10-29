package edu.ncsu.csc216.pack_scheduler.catalog;

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

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Tests CourseCatalog class
 * 
 * @author Gabe Frain
 * @author Justin Pak
 * @author Bryce Yang
 */
public class CourseCatalogTest {

	/** Valid course records */
	private final String validTestFile = "test-files/course_records.txt";
	/** Invalid course records */
	private final String invalidTestFile = "test-files/invalid_records.txt";

	/** Course name */
	private static final String NAME = "CSC216";
	/** Course title */
	private static final String TITLE = "Software Development Fundamentals";
	/** Course section */
	private static final String SECTION = "001";
	/** Course credits */
	private static final int CREDITS = 3;
	/** Course instructor id */
	private static final String INSTRUCTOR_ID = "sesmith5";
	/** Course meeting days */
	private static final String MEETING_DAYS = "TH";
	/** Course start time */
	private static final int START_TIME = 1330;
	/** Course end time */
	private static final int END_TIME = 1445;

	/**
	 * Resets course_records.txt for use in other tests.
	 */
	@Before
	public void setUp() throws Exception {
		// Reset course_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "starter_course_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "course_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}

	/**
	 * Tests the CourseCatalog method
	 */
	@Test
	public void testCourseCatalog() {
		// Test that the CourseCatalog is initialized to an empty list
		CourseCatalog cc = new CourseCatalog();
		assertFalse(cc.removeCourseFromCatalog(NAME, SECTION));
		assertEquals(0, cc.getCourseCatalog().length);
	}

	/**
	 * Tests the newCourseCatalog method
	 */
	@Test
	public void testNewCourseCatalog() {
		// Test that if there are courses in the catalog, they
		// are removed after calling newCourseCatalog().
		CourseCatalog cc = new CourseCatalog();

		cc.loadCoursesFromFile(validTestFile);
		assertEquals(13, cc.getCourseCatalog().length);

		cc.newCourseCatalog();
		assertEquals(0, cc.getCourseCatalog().length);
	}

	/**
	 * Tests the loadCoursesFromFile method
	 */
	@Test
	public void testLoadCoursesFromFile() {
		CourseCatalog cc = new CourseCatalog();

		// Test valid file
		cc.loadCoursesFromFile(validTestFile);
		assertEquals(13, cc.getCourseCatalog().length);

		// Test invalid file
		Exception e = assertThrows(IllegalArgumentException.class, () -> cc.loadCoursesFromFile(invalidTestFile));
		assertEquals("Unable to read file " + invalidTestFile, e.getMessage());
	}

	/**
	 * Tests the addCourse method.
	 */
	@Test
	public void testAddCourse() {
		CourseCatalog cc = new CourseCatalog();

		// Test valid Course
		cc.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, MEETING_DAYS, START_TIME, END_TIME);
		String[][] coursecatalog = cc.getCourseCatalog();
		assertEquals(1, coursecatalog.length);
		assertEquals(NAME, coursecatalog[0][0]);
		assertEquals(SECTION, coursecatalog[0][1]);
		assertEquals(TITLE, coursecatalog[0][2]);
		Course c = cc.getCourseFromCatalog(NAME, SECTION);
		assertEquals(c.getMeetingString(), coursecatalog[0][3]);

		// Test null name
		Exception nn = assertThrows(IllegalArgumentException.class, () -> cc.addCourseToCatalog(null, TITLE, SECTION,
				CREDITS, INSTRUCTOR_ID, MEETING_DAYS, START_TIME, END_TIME));
		assertEquals("Invalid course name.", nn.getMessage());
		// Test short name
		Exception sn = assertThrows(IllegalArgumentException.class, () -> cc.addCourseToCatalog("CS 1", TITLE, SECTION,
				CREDITS, INSTRUCTOR_ID, MEETING_DAYS, START_TIME, END_TIME));
		assertEquals("Invalid course name.", sn.getMessage());
		// Test long name
		Exception ln = assertThrows(IllegalArgumentException.class, () -> cc.addCourseToCatalog("ASPY 2009", TITLE,
				SECTION, CREDITS, INSTRUCTOR_ID, MEETING_DAYS, START_TIME, END_TIME));
		assertEquals("Invalid course name.", ln.getMessage());

		// Test null section
		Exception ns = assertThrows(IllegalArgumentException.class, () -> cc.addCourseToCatalog(NAME, TITLE, null,
				CREDITS, INSTRUCTOR_ID, MEETING_DAYS, START_TIME, END_TIME));
		assertEquals("Invalid section.", ns.getMessage());
		// Test section with not 3 characters
		Exception ds = assertThrows(IllegalArgumentException.class, () -> cc.addCourseToCatalog(NAME, TITLE, "8190",
				CREDITS, INSTRUCTOR_ID, MEETING_DAYS, START_TIME, END_TIME));
		assertEquals("Invalid section.", ds.getMessage());
		// Test section with not only digits
		Exception cs = assertThrows(IllegalArgumentException.class, () -> cc.addCourseToCatalog(NAME, TITLE, "81A",
				CREDITS, INSTRUCTOR_ID, MEETING_DAYS, START_TIME, END_TIME));
		assertEquals("Invalid section.", cs.getMessage());

		// Test not enough credits
		Exception lc = assertThrows(IllegalArgumentException.class, () -> cc.addCourseToCatalog(NAME, TITLE, SECTION, 0,
				INSTRUCTOR_ID, MEETING_DAYS, START_TIME, END_TIME));
		assertEquals("Invalid credits.", lc.getMessage());
		// Test too many credits
		Exception mc = assertThrows(IllegalArgumentException.class, () -> cc.addCourseToCatalog(NAME, TITLE, SECTION,
				32, INSTRUCTOR_ID, MEETING_DAYS, START_TIME, END_TIME));
		assertEquals("Invalid credits.", mc.getMessage());

		// Test null instructor id
		Exception ni = assertThrows(IllegalArgumentException.class,
				() -> cc.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, null, MEETING_DAYS, START_TIME, END_TIME));
		assertEquals("Invalid instructor id.", ni.getMessage());
		// Test empty instructor id
		Exception ei = assertThrows(IllegalArgumentException.class,
				() -> cc.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, "", MEETING_DAYS, START_TIME, END_TIME));
		assertEquals("Invalid instructor id.", ei.getMessage());

		// Test fake meeting day
		Exception fm = assertThrows(IllegalArgumentException.class,
				() -> cc.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, "FP", START_TIME, END_TIME));
		assertEquals("Invalid meeting days and times.", fm.getMessage());
		// Test meeting days of A and F
		Exception am = assertThrows(IllegalArgumentException.class,
				() -> cc.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, "AF", START_TIME, END_TIME));
		assertEquals("Invalid meeting days and times.", am.getMessage());
		// Test duplicate meeting day
		Exception dm = assertThrows(IllegalArgumentException.class, () -> cc.addCourseToCatalog(NAME, TITLE, SECTION,
				CREDITS, INSTRUCTOR_ID, "MWHM", START_TIME, END_TIME));
		assertEquals("Invalid meeting days and times.", dm.getMessage());

	}

	/**
	 * Tests the removeCourseFromCatalog method
	 */
	@Test
	public void testRemoveCourseFromCatalog() {
		CourseCatalog cc = new CourseCatalog();

		// Add courses and remove
		cc.loadCoursesFromFile(validTestFile);
		assertEquals(13, cc.getCourseCatalog().length);
		assertTrue(cc.removeCourseFromCatalog("CSC217", "223"));
		String[][] coursecatalog = cc.getCourseCatalog();
		assertEquals(12, coursecatalog.length);
	}

	/**
	 * Tests the getCourseFromCatalogStudent method
	 */
	@Test
	public void testGetCourseFromCatalog() {
		CourseCatalog cc = new CourseCatalog();

		// Add courses and remove
		cc.loadCoursesFromFile(validTestFile);
		assertEquals(13, cc.getCourseCatalog().length);
		Course c = cc.getCourseFromCatalog(NAME, SECTION);
		assertEquals(NAME, c.getName());
		assertEquals(SECTION, c.getSection());
		assertEquals(TITLE, c.getTitle());
	}

	/**
	 * Tests the saveCourseCatalog method
	 */
	@Test
	public void testSaveCourseCatalog() {
		CourseCatalog cc = new CourseCatalog();

		// Add a course
		cc.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, MEETING_DAYS, START_TIME, END_TIME);
		assertEquals(1, cc.getCourseCatalog().length);

		// Valid File
		cc.saveCourseCatalog("test-files/actual_course_records.txt");
		checkFiles("test-files/expected_course_records1.txt", "test-files/actual_course_records.txt");

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