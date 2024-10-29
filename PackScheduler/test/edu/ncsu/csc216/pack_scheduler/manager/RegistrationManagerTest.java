package edu.ncsu.csc216.pack_scheduler.manager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;

/**
 * Tests the RegistrationManager Class
 * 
 * @author Rujul Waval
 * @author Gabe Frain
 * @author Arjun Kancha
 */
public class RegistrationManagerTest {
	
	/** A instance of the registration manager */
	private RegistrationManager manager;
	
	/**
	 * Sets up the RegistrationManager and clears the data.
	 * @throws Exception if error
	 */
	@BeforeEach
	public void setUp() throws Exception {
		manager = RegistrationManager.getInstance();
		manager.clearData();
	}

	/**
	 * Tests get course catalog in the registration manager
	 */
	@Test
	public void testGetCourseCatalog() {
		CourseCatalog courses = manager.getCourseCatalog();
		courses.addCourseToCatalog("CSC216", "Software", "003", 3, "ID", "A", 0, 0);
		assertEquals("CSC216", courses.getCourseCatalog()[0][0]);
		assertEquals("003", courses.getCourseCatalog()[0][1]);
		assertEquals("Software", courses.getCourseCatalog()[0][2]);
	}

	/**
	 * Tests get student directory in the registration manager
	 */
	@Test
	public void testGetStudentDirectory() {
		StudentDirectory students = manager.getStudentDirectory();
		students.addStudent("John", "Doe", "jdoe", "jdoe@ncsu.edu", "state", "state", 0);
		assertEquals("John", students.getStudentDirectory()[0][0]);
		assertEquals("Doe", students.getStudentDirectory()[0][1]);
		assertEquals("jdoe", students.getStudentDirectory()[0][2]);
	}

	/**
	 * Tests logging in to the registration manager
	 */
	@Test
	public void testLogin() {
		StudentDirectory students = manager.getStudentDirectory();
		students.addStudent("John", "Doe", "jdoe", "jdoe@ncsu.edu", "state", "state", 0);
		
		assertTrue(manager.login("jdoe", "state"));

		assertFalse(manager.login("jdoe", "wolfpack"));
		
		manager.logout();
		Exception e = assertThrows(IllegalArgumentException.class, () -> manager.login("Paul", "83js"));
		assertEquals("User doesn't exist.", e.getMessage());
	}
	
	/**
	 * Tests logging out of the registration manager
	 */
	@Test
	public void testLogout() {
		manager.logout();
		assertNull(manager.getCurrentUser());
	}

	/**
	 * Tests get current user in the registration manager
	 */
	@Test
	public void testGetCurrentUser() {
		StudentDirectory studentdir = manager.getStudentDirectory();
		studentdir.addStudent("John", "Doe", "jdoe", "jdoe@ncsu.edu", "state", "state", 0);
		manager.login("jdoe", "state");
		assertEquals("jdoe", manager.getCurrentUser().getId());
		manager.logout();
	}

}