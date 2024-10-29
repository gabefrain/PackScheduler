package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Tests the ArrayList class
 * 
 * @author Gabe Frain
 */
public class ArrayListTest {

	/**
	 * Tests the constructor of ArrayList
	 */
	@Test
	public void testArrayList() {
		ArrayList<String> list = new ArrayList<String>();
		assertEquals(0, list.size());
	}

	/**
	 * Tests the add method
	 */
	@Test
	public void testAdd() {
		ArrayList<String> list = new ArrayList<String>();

		// Adding at beginning
		list.add(0, "First Addition");
		list.add(0, "Second Addition");
		assertEquals("Second Addition", list.get(0));
		assertEquals("First Addition", list.get(1));

		// Adding in middle
		list.add(1, "Third Addition");
		assertEquals("Second Addition", list.get(0));
		assertEquals("Third Addition", list.get(1));
		assertEquals("First Addition", list.get(2));

		// Adding at end
		list.add(3, "Fourth Addition");
		assertEquals("Second Addition", list.get(0));
		assertEquals("Third Addition", list.get(1));
		assertEquals("First Addition", list.get(2));
		assertEquals("Fourth Addition", list.get(3));

		// Testing that you can add past initial capacity
		list.add(4, "5th");
		list.add(5, "6th");
		list.add(6, "7th");
		list.add(7, "8th");
		list.add(8, "9th");
		list.add(9, "10th");
		list.add(10, "11th");
		assertEquals("11th", list.get(10));

		// Invalid due to null element
		Exception e1 = assertThrows(NullPointerException.class, () -> list.add(0, null));
		assertEquals(null, e1.getMessage());

		// Invalid due to invalid indexes
		Exception e2 = assertThrows(IndexOutOfBoundsException.class, () -> list.add(-4, "Element"));
		assertEquals(null, e2.getMessage());
		Exception e3 = assertThrows(IndexOutOfBoundsException.class, () -> list.add(18, "Element"));
		assertEquals(null, e3.getMessage());

		// Invalid due to duplicate
		Exception e4 = assertThrows(IllegalArgumentException.class, () -> list.add(8, "Second Addition"));
		assertEquals(null, e4.getMessage());

	}

	/**
	 * Tests the remove method
	 */
	@Test
	public void remove() {
		ArrayList<String> list = new ArrayList<String>();
		list.add(0, "First Addition");
		list.add(1, "Second Addition");
		list.add(2, "Third Addition");
		list.add(3, "Fourth Addition");

		// Removing from beginning
		list.remove(0);
		assertEquals("Second Addition", list.get(0));
		assertEquals("Third Addition", list.get(1));
		assertEquals("Fourth Addition", list.get(2));
		assertEquals(3, list.size());

		// Removing from middle
		list.remove(1);
		assertEquals("Second Addition", list.get(0));
		assertEquals("Fourth Addition", list.get(1));
		assertEquals(2, list.size());

		// Removing from end
		list.remove(1);
		assertEquals("Second Addition", list.get(0));
		assertEquals(1, list.size());

		// Invalid due to invalid indexes
		Exception e1 = assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
		assertEquals(null, e1.getMessage());
		Exception e2 = assertThrows(IndexOutOfBoundsException.class, () -> list.remove(6));
		assertEquals(null, e2.getMessage());

	}

	/**
	 * Tests the set method
	 */
	@Test
	public void set() {
		ArrayList<String> list = new ArrayList<String>();
		list.add(0, "First Addition");
		list.add(1, "Second Addition");
		list.add(2, "Third Addition");
		list.add(3, "Fourth Addition");

		// Valid setting
		assertEquals(4, list.size());
		assertEquals("Third Addition", list.set(2, "Fifth"));
		assertEquals("Fifth", list.get(2));
		assertEquals(4, list.size());

		// Invalid due to null element
		Exception e1 = assertThrows(NullPointerException.class, () -> list.set(1, null));
		assertEquals(null, e1.getMessage());

		// Invalid due to invalid indexes
		Exception e2 = assertThrows(IndexOutOfBoundsException.class, () -> list.set(-4, "Sixth"));
		assertEquals(null, e2.getMessage());
		Exception e3 = assertThrows(IndexOutOfBoundsException.class, () -> list.set(8, "Sixth"));
		assertEquals(null, e3.getMessage());

		// Invalid due to duplicate
		Exception e4 = assertThrows(IllegalArgumentException.class, () -> list.set(3, "Second Addition"));
		assertEquals(null, e4.getMessage());

	}
	
	/**
	 * Tests the get method
	 */
	@Test
	public void get() {
		ArrayList<String> list = new ArrayList<String>();
		list.add(0, "First Addition");
		list.add(1, "Second Addition");
		list.add(2, "Third Addition");
		
		// Valid getting
		assertEquals("Second Addition", list.get(1));
		
		// Invalid getting
		Exception e1 = assertThrows(IndexOutOfBoundsException.class, () -> list.set(-2, "Second Addition"));
		assertEquals(null, e1.getMessage());
		Exception e2 = assertThrows(IndexOutOfBoundsException.class, () -> list.set(6, "Second Addition"));
		assertEquals(null, e2.getMessage());
	}
	
	/**
	 * Tests the size method
	 */
	@Test
	public void size() {
		ArrayList<String> list = new ArrayList<String>();
		list.add(0, "First Addition");
		list.add(1, "Second Addition");
		list.add(2, "Third Addition");
		assertEquals(3, list.size());
	}

}
