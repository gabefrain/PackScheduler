package edu.ncsu.csc217.collections.list;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

/**
 * Unit test cases of sorted list
 * 
 * @author Bryce Yang
 * @author Justin Pak
 * @author Gabe Frain
 */
public class SortedListTest {

	/**
	 * Tests the SortedList method
	 */
	@Test
	public void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());
		assertFalse(list.contains("apple"));

		list.add("List Item 1");
		assertEquals(1, list.size());
		list.add("List Item 2");
		assertEquals(2, list.size());
		list.add("List Item 3");
		assertEquals(3, list.size());
		list.add("List Item 4");
		assertEquals(4, list.size());
		list.add("List Item 5");
		assertEquals(5, list.size());
		list.add("List Item 6");
		assertEquals(6, list.size());
		list.add("List Item 7");
		assertEquals(7, list.size());
		list.add("List Item 8");
		assertEquals(8, list.size());
		list.add("List Item 9");
		assertEquals(9, list.size());
		list.add("List Item 10");
		assertEquals(10, list.size());
		list.add("List Item 11");
		assertEquals(11, list.size());

	}

	/**
	 * Tests the Add method
	 */
	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();

		list.add("banana");
		assertEquals(1, list.size());
		assertEquals("banana", list.get(0));
		// Element to the front
		list.add("apple");
		assertEquals(2, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));
		// Element to the end
		list.add("zoo");
		assertEquals(3, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("zoo", list.get(2));
		// Element to the middle
		list.add("china");
		assertEquals(4, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("china", list.get(2));
		assertEquals("zoo", list.get(3));
		// null element
		Exception npe = assertThrows(NullPointerException.class,
				() -> list.add(null));
		assertEquals(null, npe.getMessage());
		assertEquals(4, list.size());
		// duplicate element
		Exception iae = assertThrows(IllegalArgumentException.class,
				() -> list.add("apple"));
		assertEquals("Element already in list.", iae.getMessage());
		assertEquals(4, list.size());

	}

	/**
	 * Tests the Get method
	 */
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();
		// Test getting an element from an empty list
		Exception ibe1 = assertThrows(IndexOutOfBoundsException.class,
				() -> list.get(0));
		assertEquals(null, ibe1.getMessage());
		// Add some elements to the list
		list.add("1");
		list.add("2");
		list.add("3");
		// Test getting an element at an index < 0
		Exception ibe2 = assertThrows(IndexOutOfBoundsException.class,
				() -> list.get(-1));
		assertEquals(null, ibe2.getMessage());
		// Test getting an element at size
		Exception ibe3 = assertThrows(IndexOutOfBoundsException.class,
				() -> list.get(list.size()));
		assertEquals(null, ibe3.getMessage());
	}

	/**
	 * Tests the Remove method
	 */
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();

		// Test removing from an empty list
		Exception ibe1 = assertThrows(IndexOutOfBoundsException.class,
				() -> list.remove(0));
		assertEquals(null, ibe1.getMessage());

		// Add some elements to the list - at least 4
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");

		// Test removing an element at an index < 0
		Exception ibe2 = assertThrows(IndexOutOfBoundsException.class,
				() -> list.remove(-1));
		assertEquals(null, ibe2.getMessage());

		// Test removing an element at size
		Exception ibe3 = assertThrows(IndexOutOfBoundsException.class,
				() -> list.remove(list.size()));
		assertEquals(null, ibe3.getMessage());

		// Test removing a middle element
		list.remove(1);
		assertEquals("1", list.get(0));
		assertEquals("3", list.get(1));
		assertEquals("4", list.get(2));

		// Test removing the last element
		list.remove(2);
		assertEquals("1", list.get(0));
		assertEquals("3", list.get(1));

		// Test removing the first element
		list.remove(0);
		assertEquals("3", list.get(0));

		// Test removing the last element
		list.remove(0);
		assertEquals(0, list.size());
	}

	/**
	 * Tests the IndexOf method
	 */
	@Test
	public void testIndexOf() {
		SortedList<String> list = new SortedList<String>();

		// Test indexOf on an empty list
		assertEquals(-1, list.indexOf("0"));

		// Add some elements
		list.add("1");
		list.add("2");

		// Test various calls to indexOf for elements in the list
		// and not in the list
		assertEquals(0, list.indexOf("1"));
		assertEquals(-1, list.indexOf("-1"));
		assertEquals(1, list.indexOf("2"));

		// Test checking the index of null
		Exception npe = assertThrows(NullPointerException.class,
				() -> list.indexOf(null));
		assertEquals(null, npe.getMessage());
	}

	/**
	 * Tests the Clear method
	 */
	@Test
	public void testClear() {
		SortedList<String> list = new SortedList<String>();

		// Add some elements
		list.add("1");
		list.add("2");
		list.add("3");

		// Clear the list
		list.clear();

		// Test that the list is empty
		assertTrue(list.isEmpty());
		
	}

	/**
	 * Tests the IsEmpty method
	 */
	@Test
	public void testIsEmpty() {
		SortedList<String> list = new SortedList<String>();

		// Test that the list starts empty
		assertTrue(list.isEmpty());

		// Add at least one element
		list.add("1");

		// Check that the list is no longer empty
		assertFalse(list.isEmpty());
	}

	/**
	 * Tests the Contains method
	 */
	@Test
	public void testContains() {
		SortedList<String> list = new SortedList<String>();

		// Test the empty list case
		assertFalse(list.contains("7"));

		// Add some elements
		list.add("5");
		list.add("1");
		list.add("13");
		list.add("6");

		// Test some true and false cases
		assertTrue(list.contains("1"));
		assertTrue(list.contains("6"));
		assertFalse(list.contains("try"));
	}

	/**
	 * Tests the Equals method
	 */
	@Test
	public void testEquals() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();

		// Make two lists the same and one list different
		list1.add("apple");
		list1.add("12");
		list2.add("apple");
		list2.add("12");
		list3.add("orange");
		list3.add("0");

		// Test for equality and non-equality
		assertEquals(list1, list2);
		assertNotEquals(list1, list3);
		assertNotEquals(list2, list3);

	}

	/**
	 * Tests the HashCode method
	 */
	@Test
	public void testHashCode() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();

		// Make two lists the same and one list different
		list1.add("apple");
		list1.add("12");
		list2.add("apple");
		list2.add("12");
		list3.add("orange");
		list3.add("0");

		// Test for the same and different hashCodes
		assertEquals(list1.hashCode(), list2.hashCode());
		assertEquals(list2.hashCode(), list1.hashCode());
		assertNotEquals(list1.hashCode(), list3.hashCode());
		assertNotEquals(list3.hashCode(), list1.hashCode());
	}

}