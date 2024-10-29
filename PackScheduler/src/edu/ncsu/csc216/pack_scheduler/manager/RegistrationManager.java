package edu.ncsu.csc216.pack_scheduler.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Properties;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;

/**
 * Manages the registration process, including login, logout, course catalog,
 * and student directory.
 * 
 * @author Rujul Waval
 * @author Gabe Frain
 * @author Arjun Kancha
 */
public class RegistrationManager {

	/** Singleton instance of RegistrationManager */
	private static RegistrationManager instance;
	/** Catalog of courses */
	private CourseCatalog courseCatalog;
	/** Directory of students */
	private StudentDirectory studentDirectory;
	/** The registrar that handles the courses */
	private User registrar;
	/** The currently logged-in user */
	private User currentUser;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	/** Path to the registrar properties file */
	private static final String PROP_FILE = "registrar.properties";

	/**
	 * Private constructor for RegistrationManager to enforce singleton pattern.
	 */
	private RegistrationManager() {
		courseCatalog = new CourseCatalog();
		studentDirectory = new StudentDirectory();
		createRegistrar();
	}

	/**
	 * Loads the registrar's information from a properties file.
	 */
	private void createRegistrar() {
		Properties prop = new Properties();

		try (InputStream input = new FileInputStream(PROP_FILE)) {
			prop.load(input);

			String hashPW = hashPW(prop.getProperty("pw"));

			registrar = new Registrar(prop.getProperty("first"), prop.getProperty("last"), prop.getProperty("id"),
					prop.getProperty("email"), hashPW);
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot create registrar.");
		}
	}

	/**
	 * Hashes the given password using SHA-256.
	 * 
	 * @param pw the plain-text password
	 * @return the hashed password as a Base64 string
	 */
	private String hashPW(String pw) {
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(pw.getBytes());
			return Base64.getEncoder().encodeToString(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
	}

	/**
	 * Returns the singleton instance of RegistrationManager.
	 * 
	 * @return the singleton instance of RegistrationManager
	 */
	public static synchronized RegistrationManager getInstance() {
		if (instance == null) {
			instance = new RegistrationManager();
		}
		return instance;
	}

	/**
	 * Returns the course catalog.
	 * 
	 * @return the course catalog
	 */
	public CourseCatalog getCourseCatalog() {
		return courseCatalog;
	}

	/**
	 * Returns the student directory.
	 * 
	 * @return the student directory
	 */
	public StudentDirectory getStudentDirectory() {
		return studentDirectory;
	}

	/**
	 * Attempts to log in a user by their ID and password.
	 * 
	 * @param id       the user's ID
	 * @param password the user's plain-text password
	 * @return true if login is successful, false otherwise
	 * @throws IllegalArgumentException when s is null
	 */
	public boolean login(String id, String password) {

		if (currentUser != null) {
			return false;
		}
		
		String localHashPW = hashPW(password);
		if (registrar.getId().equals(id) && registrar.getPassword().equals(localHashPW)) {
			currentUser = registrar;
			return true;
		}
		
		if (registrar.getId().equals(id) && !registrar.getPassword().equals(localHashPW)) {
			return false;
		}
		
		Student s = studentDirectory.getStudentById(id);
		
		if (s == null) {
			throw new IllegalArgumentException("User doesn't exist.");
		}
		
		if (s.getPassword().equals(localHashPW)) {
			currentUser = s;
			return true;
		}

		return false;
	}

	/**
	 * Logs out the current user.
	 */
	public void logout() {
		currentUser = null;
	}

	/**
	 * Returns the currently logged-in user.
	 * 
	 * @return the currently logged-in user, or null if no user is logged in
	 */
	public User getCurrentUser() {
		return currentUser;
	}

	/**
	 * Clears all data from the course catalog and student directory.
	 */
	public void clearData() {
		courseCatalog.newCourseCatalog();
		studentDirectory.newStudentDirectory();
	}

	/**
	 * Inner class representing the system's registrar.
	 */
	private static class Registrar extends User {
		/**
		 * Create a registrar user
		 * 
		 * @param firstName the first name of the registrar
		 * @param lastName  the last name of the registrar
		 * @param id        the ID of the registrar
		 * @param email     the email of the registrar
		 * @param hashPW    the hashed password of the registrar
		 */
		public Registrar(String firstName, String lastName, String id, String email, String hashPW) {
			super(firstName, lastName, id, email, hashPW);
		}
	}
}