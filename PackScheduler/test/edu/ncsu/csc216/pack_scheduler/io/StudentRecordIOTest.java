package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * Tests the functionality of the StudentRecordIO class. Tests methods
 * readStudentRecords() and writeStudentRecords().
 * 
 * @author Bryce Yang
 * @author Gabe Frain
 * @author Justin Pak
 */

class StudentRecordIOTest {

	/**
	 * The correct array length for an array of students, based off of the
	 * student_records.txt file.
	 */
	static final int CORRECT_ARRAY_LENGTH = 10;
	/** A test file of valid student records in String format. */
	private static final String VALID_TEST_FILE = "test-files/student_records.txt";
	/** A test file of invalid student records in String format. */
	private static final String INVALID_TEST_FILE = "test-files/invalid_student_records.txt";
	/** the 1st valid student in the Array. */
	private String validStudent0 = "Zahir,King,zking,orci.Donec@ametmassaQuisque.com,pw,15";
	/** the 2nd valid student in the Array. */
	private String validStudent1 = "Cassandra,Schwartz,cschwartz,semper@imperdietornare.co.uk,pw,4";
	/** the 3rd valid student in the Array. */
	private String validStudent2 = "Shannon,Hansen,shansen,convallis.est.vitae@arcu.ca,pw,14";
	/** the 4th valid student in the Array. */
	private String validStudent3 = "Demetrius,Austin,daustin,Curabitur.egestas.nunc@placeratorcilacus.co.uk,pw,18";
	/** the 5th valid student in the Array. */
	private String validStudent4 = "Raymond,Brennan,rbrennan,litora.torquent@pellentesquemassalobortis.ca,pw,12";
	/** the 6th valid student in the Array. */
	private String validStudent5 = "Emerald,Frost,efrost,adipiscing@acipsumPhasellus.edu,pw,3";
	/** the 7th valid student in the Array. */
	private String validStudent6 = "Lane,Berg,lberg,sociis@non.org,pw,14";
	/** the 8th valid student in the Array. */
	private String validStudent7 = "Griffith,Stone,gstone,porta@magnamalesuadavel.net,pw,17";
	/** the 9th valid student in the Array. */
	private String validStudent8 = "Althea,Hicks,ahicks,Phasellus.dapibus@luctusfelis.com,pw,11";
	/** the 10th valid student in the Array. */
	private String validStudent9 = "Dylan,Nolan,dnolan,placerat.Cras.dictum@dictum.net,pw,5";
	/** An Array of student information in String form. */
	private String[] validStudents = { validStudent3, validStudent6, validStudent4, validStudent5, validStudent2,
			validStudent8, validStudent0, validStudent9, validStudent1, validStudent7 };
	/** The hashed version of the password. */
	private String hashPW;
	/** Hash algorithm. */
	private static final String HASH_ALGORITHM = "SHA-256";

	/**
	 * Helper method to compare two files for the same contents
	 * 
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new FileInputStream(expFile));
				Scanner actScanner = new Scanner(new FileInputStream(actFile));) {

			while (expScanner.hasNextLine() && actScanner.hasNextLine()) {
				String exp = expScanner.nextLine();
				String act = actScanner.nextLine();
				assertEquals(exp, act, "Expected: " + exp + " Actual: " + act);
				// The third argument helps with debugging!
			}
			if (expScanner.hasNextLine()) {
				fail("The expected results expect another line " + expScanner.nextLine());
			}
			if (actScanner.hasNextLine()) {
				fail("The actual results has an extra, unexpected line: " + actScanner.nextLine());
			}

			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}

	@BeforeEach
	public void setUp() {
		try {
			String password = "pw";
			MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
			digest.update(password.getBytes());
			hashPW = Base64.getEncoder().encodeToString(digest.digest());

			for (int i = 0; i < validStudents.length; i++) {
				validStudents[i] = validStudents[i].replace(",pw,", "," + hashPW + ",");
			}
		} catch (NoSuchAlgorithmException e) {
			fail("Unable to create hash during setup");
		}
	}

	/**
	 * Test method for ReadStudentRecords with valid records
	 */
	@Test
	void testReadValidStudentRecords() {
		SortedList<Student> studentsFromRecord = new SortedList<Student>();
		try {
			studentsFromRecord = StudentRecordIO.readStudentRecords(VALID_TEST_FILE);
			assertEquals(studentsFromRecord.size(), CORRECT_ARRAY_LENGTH);
			for (int i = 0; i < studentsFromRecord.size(); i++) {
				assertEquals(studentsFromRecord.get(i).toString(), validStudents[i]);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			fail("Unexpected error reading " + VALID_TEST_FILE);
		}
	}

	/**
	 * Test method for ReadStudentRecords with invalid records
	 */
	@Test
	void testReadInvalidStudentRecords() {
		SortedList<Student> studentsFromRecord;
		try {
			studentsFromRecord = StudentRecordIO.readStudentRecords(INVALID_TEST_FILE);
			assertEquals(0, studentsFromRecord.size());
		} catch (FileNotFoundException e) {
			fail("Unexpected FileNotFoundException");
		}
	}

	/**
	 * Test method for WriteStudentRecords with no permission
	 */
	@Test
	public void testWriteStudentRecordsNoPermissions() {
		SortedList<Student> students = new SortedList<Student>();
		students.add(new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 15));

		Exception exception = assertThrows(IOException.class,
				() -> StudentRecordIO.writeStudentRecords("/home/sesmith5/actual_student_records.txt", students));
		assertEquals("/home/sesmith5/actual_student_records.txt (No such file or directory)", exception.getMessage());
	}

	/**
	 * Test method for WriteStudentRecords
	 */
	@Test
	void testWriteStudentRecords() {
		SortedList<Student> students = new SortedList<Student>();
		students.add(new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 15));
		students.add(new Student("Cassandra", "Schwartz", "cschwartz", "semper@imperdietornare.co.uk", hashPW, 4));
		students.add(new Student("Shannon", "Hansen", "shansen", "convallis.est.vitae@arcu.ca", hashPW, 14));
		students.add(new Student("Demetrius", "Austin", "daustin", "Curabitur.egestas.nunc@placeratorcilacus.co.uk",
				hashPW, 18));
		students.add(new Student("Raymond", "Brennan", "rbrennan", "litora.torquent@pellentesquemassalobortis.ca",
				hashPW, 12));
		students.add(new Student("Emerald", "Frost", "efrost", "adipiscing@acipsumPhasellus.edu", hashPW, 3));
		students.add(new Student("Lane", "Berg", "lberg", "sociis@non.org", hashPW, 14));
		students.add(new Student("Griffith", "Stone", "gstone", "porta@magnamalesuadavel.net", hashPW, 17));
		students.add(new Student("Althea", "Hicks", "ahicks", "Phasellus.dapibus@luctusfelis.com", hashPW, 11));
		students.add(new Student("Dylan", "Nolan", "dnolan", "placerat.Cras.dictum@dictum.net", hashPW, 5));

		try {
			StudentRecordIO.writeStudentRecords("test-files/actual_student_records.txt", students);
		} catch (IOException e) {
			fail("Cannot write to student records file");
		}

		checkFiles("test-files/expected_student_records2.txt", "test-files/actual_student_records.txt");
	}

}
