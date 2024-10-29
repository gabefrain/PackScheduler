/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Unit Test Cases for Student class
 * 
 * @author Justin Pak
 * @author Bryce Yang
 * @author Gabe Frain
 */
class StudentTest {

	/** Student firt name */
	private static final String FNAME = "First";
	/** Student last name */
	private static final String LNAME = "Last";
	/** Student id */
	private static final String ID = "ID";
	/** Student id empty */
	private static final String EID = "";
	/** Student id null */
	private static final String NID = null;
	/** Student email */
	private static final String EMAIL = "email@ncsu.edu";
	/** Student password */
	private static final String PASSWORD = "hashPW";
	/** Student max credits */
	private static final int MAX_CREDIT_HOURS = 18;
	/** Student less than max credits */
	private static final int LITTLE_CREDIT_HOURS = 2;
	/** Student more than max credits */
	private static final int MANY_CREDIT_HOURS = 24;
	/** Different student first name */
	private static final String DFNAME = "tsriF";
	/** Different student student last name */
	private static final String DLNAME = "tsaL";
	/** Different student id */
	private static final String DID = "DI";
	/** Different student email */
	private static final String DEMAIL = "liame@ncsu.edu";
	/** Different student password */
	private static final String DPASSWORD = "WPhsah";
	/** Different student max credits */
	private static final int DMAX_CREDIT_HOURS = 12;

	/**
	 * Test method for full constructor of student
	 */
	@Test
	void testStudentStringStringStringStringStringInt() {
		Student s = assertDoesNotThrow(() -> new Student(FNAME, LNAME, ID, EMAIL, PASSWORD, MAX_CREDIT_HOURS),
				"Should not throw exception");
		assertAll("Course", () -> assertEquals(FNAME, s.getFirstName(), "incorrect name"),
				() -> assertEquals(LNAME, s.getLastName(), "incorrect title"),
				() -> assertEquals(ID, s.getId(), "incorrect section"),
				() -> assertEquals(EMAIL, s.getEmail(), "incorrect credits"),
				() -> assertEquals(PASSWORD, s.getPassword(), "incorrect instructor id"),
				() -> assertEquals(MAX_CREDIT_HOURS, s.getMaxCredits(), "incorrect meeting days"));
	}

	/**
	 * Test method for partial constructor of student
	 */
	@Test
	void testStudentStringStringStringStringString() {
		Student s = assertDoesNotThrow(() -> new Student(FNAME, LNAME, ID, EMAIL, PASSWORD),
				"Should not throw exception");
		assertAll("Course", () -> assertEquals(FNAME, s.getFirstName(), "incorrect name"),
				() -> assertEquals(LNAME, s.getLastName(), "incorrect title"),
				() -> assertEquals(ID, s.getId(), "incorrect section"),
				() -> assertEquals(EMAIL, s.getEmail(), "incorrect credits"),
				() -> assertEquals(PASSWORD, s.getPassword(), "incorrect instructor id"));
	}

	/**
	 * Test SetFirstName
	 */
	@Test
	void testSetFirstName() {
		Student s = new Student(FNAME, LNAME, ID, EMAIL, PASSWORD);

		// Test null first name
		Exception nfn = assertThrows(IllegalArgumentException.class, () -> s.setFirstName(null));
		assertEquals("Invalid first name", nfn.getMessage());
		assertEquals("First", s.getFirstName());

		// Test empty first name
		Exception efn = assertThrows(IllegalArgumentException.class, () -> s.setFirstName(""));
		assertEquals("Invalid first name", efn.getMessage());
		assertEquals("First", s.getFirstName());

	}

	/**
	 * Test SetLastName
	 */
	@Test
	void testSetLastName() {
		Student s = new Student(FNAME, LNAME, ID, EMAIL, PASSWORD);

		// Test null last name
		Exception nln = assertThrows(IllegalArgumentException.class, () -> s.setLastName(null));
		assertEquals("Invalid last name", nln.getMessage());
		assertEquals("Last", s.getLastName());

		// Test empty last name
		Exception eln = assertThrows(IllegalArgumentException.class, () -> s.setLastName(""));
		assertEquals("Invalid last name", eln.getMessage());
		assertEquals("Last", s.getLastName());

	}

	/**
	 * Test SetID
	 */
	@Test
	void testSetId() {

		// Test null id
		Exception ni = assertThrows(IllegalArgumentException.class,
				() -> new Student(FNAME, LNAME, EID, EMAIL, PASSWORD));
		assertEquals("Invalid id", ni.getMessage());

		// Test empty id
		Exception ei = assertThrows(IllegalArgumentException.class,
				() -> new Student(FNAME, LNAME, NID, EMAIL, PASSWORD));
		assertEquals("Invalid id", ei.getMessage());

	}

	/**
	 * Test SetPassword
	 */
	@Test
	void testSetPassword() {
		Student s = new Student(FNAME, LNAME, ID, EMAIL, PASSWORD);

		// Test null password
		Exception np = assertThrows(IllegalArgumentException.class, () -> s.setPassword(null));
		assertEquals("Invalid password", np.getMessage());
		assertEquals("hashPW", s.getPassword());

		// Test empty password
		Exception ep = assertThrows(IllegalArgumentException.class, () -> s.setPassword(""));
		assertEquals("Invalid password", ep.getMessage());
		assertEquals("hashPW", s.getPassword());

	}

	/**
	 * Test SetEmail
	 */
	@Test
	void testSetEmail() {
		Student s = new Student(FNAME, LNAME, ID, EMAIL, PASSWORD);

		// Test null email
		Exception ne = assertThrows(IllegalArgumentException.class, () -> s.setEmail(null));
		assertEquals("Invalid email", ne.getMessage());
		assertEquals("email@ncsu.edu", s.getEmail());

		// Test empty email
		Exception ee = assertThrows(IllegalArgumentException.class, () -> s.setEmail(""));
		assertEquals("Invalid email", ee.getMessage());
		assertEquals("email@ncsu.edu", s.getEmail());

		// Test email with no @
		Exception ae = assertThrows(IllegalArgumentException.class, () -> s.setEmail("emailncsu.edu"));
		assertEquals("Invalid email", ae.getMessage());
		assertEquals("email@ncsu.edu", s.getEmail());

		// Test email with no .
		Exception pe = assertThrows(IllegalArgumentException.class, () -> s.setEmail("email@ncsuedu"));
		assertEquals("Invalid email", pe.getMessage());
		assertEquals("email@ncsu.edu", s.getEmail());

		// Test email with last . before @
		Exception ze = assertThrows(IllegalArgumentException.class, () -> s.setEmail("e.email@ncsuedu"));
		assertEquals("Invalid email", ze.getMessage());
		assertEquals("email@ncsu.edu", s.getEmail());

	}

	/**
	 * Test SetMaxCredits
	 */
	@Test
	void testSetMaxCredits() {
		Student s = new Student(FNAME, LNAME, ID, EMAIL, PASSWORD);

		// Test less than normal credit hours
		Exception lc = assertThrows(IllegalArgumentException.class, () -> s.setMaxCredits(LITTLE_CREDIT_HOURS));
		assertEquals("Invalid max credits", lc.getMessage());
		assertEquals(18, s.getMaxCredits());

		// Test less than normal credit hours
		Exception mc = assertThrows(IllegalArgumentException.class, () -> s.setMaxCredits(MANY_CREDIT_HOURS));
		assertEquals("Invalid max credits", mc.getMessage());
		assertEquals(18, s.getMaxCredits());
	}

	/**
	 * Test hasCode
	 */
	@Test
	void testHashCode() {
		Student s = new Student(FNAME, LNAME, ID, EMAIL, PASSWORD, MAX_CREDIT_HOURS);
		Student sc = new Student(FNAME, LNAME, ID, EMAIL, PASSWORD, MAX_CREDIT_HOURS);
		Student sf = new Student(DFNAME, LNAME, ID, EMAIL, PASSWORD, MAX_CREDIT_HOURS);
		Student sl = new Student(FNAME, DLNAME, ID, EMAIL, PASSWORD, MAX_CREDIT_HOURS);
		Student si = new Student(FNAME, LNAME, DID, EMAIL, PASSWORD, MAX_CREDIT_HOURS);
		Student se = new Student(FNAME, LNAME, ID, DEMAIL, PASSWORD, MAX_CREDIT_HOURS);
		Student sp = new Student(FNAME, LNAME, ID, EMAIL, DPASSWORD, MAX_CREDIT_HOURS);
		Student sm = new Student(FNAME, LNAME, ID, EMAIL, PASSWORD, DMAX_CREDIT_HOURS);

		// Test same state
		assertEquals(s.hashCode(), sc.hashCode());

		// Test different states
		assertNotEquals(s.hashCode(), sf.hashCode());
		assertNotEquals(s.hashCode(), sl.hashCode());
		assertNotEquals(s.hashCode(), si.hashCode());
		assertNotEquals(s.hashCode(), se.hashCode());
		assertNotEquals(s.hashCode(), sp.hashCode());
		assertNotEquals(s.hashCode(), sm.hashCode());
	}

	/**
	 * Test equalsObject
	 */
	@Test
	void testEqualsObject() {
		Student s = new Student(FNAME, LNAME, ID, EMAIL, PASSWORD, MAX_CREDIT_HOURS);
		Student sc = new Student(FNAME, LNAME, ID, EMAIL, PASSWORD, MAX_CREDIT_HOURS);
		Student sf = new Student(DFNAME, LNAME, ID, EMAIL, PASSWORD, MAX_CREDIT_HOURS);
		Student sl = new Student(FNAME, DLNAME, ID, EMAIL, PASSWORD, MAX_CREDIT_HOURS);
		Student si = new Student(FNAME, LNAME, DID, EMAIL, PASSWORD, MAX_CREDIT_HOURS);
		Student se = new Student(FNAME, LNAME, ID, DEMAIL, PASSWORD, MAX_CREDIT_HOURS);
		Student sp = new Student(FNAME, LNAME, ID, EMAIL, DPASSWORD, MAX_CREDIT_HOURS);
		Student sm = new Student(FNAME, LNAME, ID, EMAIL, PASSWORD, DMAX_CREDIT_HOURS);

		// Test same object
		assertEquals(s, s);
		// Test equal objects
		assertEquals(s, sc);
		// Test different classes
		assertNotEquals(s, "Student");
		// Test single different state
		assertNotEquals(s, sf);
		assertNotEquals(s, sl);
		assertNotEquals(s, si);
		assertNotEquals(s, se);
		assertNotEquals(s, sp);
		assertNotEquals(s, sm);

	}

	/**
	 * Test toString
	 */
	@Test
	void testToString() {
		Student c1 = new Student(FNAME, LNAME, ID, EMAIL, PASSWORD, MAX_CREDIT_HOURS);
		String s1 = "First,Last,ID,email@ncsu.edu,hashPW,18";
		assertEquals(s1, c1.toString());

		Student c2 = new Student(FNAME, LNAME, ID, EMAIL, PASSWORD);
		String s2 = "First,Last,ID,email@ncsu.edu,hashPW,18";
		assertEquals(s2, c2.toString());
	}

	/**
	 * Test compareTo
	 */
	@Test
	void testCompareTo() {
		Student s1 = new Student(FNAME, LNAME, ID, EMAIL, PASSWORD);
		Student s2 = new Student(FNAME, LNAME, ID, EMAIL, PASSWORD);
		Student s3 = new Student(FNAME, LNAME, DID, EMAIL, PASSWORD);
		Student s4 = new Student(DFNAME, LNAME, ID, EMAIL, PASSWORD);
		Student s5 = new Student(FNAME, DLNAME, ID, EMAIL, PASSWORD);

		// Test same last name, first name, and id
		assertEquals(0, s1.compareTo(s2));
		assertEquals(0, s2.compareTo(s1));

		// Test different last name
		assertEquals(-40, s1.compareTo(s5));
		assertEquals(40, s5.compareTo(s1));

		// Test different first name
		assertEquals(-46, s1.compareTo(s4));
		assertEquals(46, s4.compareTo(s1));

		// Test different id
		assertEquals(5, s1.compareTo(s3));
		assertEquals(-5, s3.compareTo(s1));
	}
}
