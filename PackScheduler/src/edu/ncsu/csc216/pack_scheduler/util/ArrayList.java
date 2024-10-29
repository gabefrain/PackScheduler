package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * A class representing an ArrayList
 * 
 * @author Gabe Frain
 * @param <E> element type of list
 */
public class ArrayList<E> extends AbstractList<E> {

	/** Initial Size of Array List */
	private static final int INIT_SIZE = 10;

	/** List of Elements */
	private E[] list;
	/** Size of List */
	private int size;

	/**
	 * Constructor of array list. Creates a object array of initial size and casts
	 * it to an E list. Also, sets the size to 0.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList() {
		list = (E[]) new Object[INIT_SIZE];
		size = 0;
	}

	/**
	 * Adds the elements at the given index
	 * 
	 * @param index   index of elements being added
	 * @param element element being added
	 * @throws IndexOutOfBoundsException if index is less than 0 or greater than
	 *                                   size
	 * @throws NullPointerException      if element is null
	 * @throws IllegalArgumentException  if element is duplicate
	 */
	@Override
	public void add(int index, E element) {
		if (element == null) {
			throw new NullPointerException();
		}
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		for (int i = 0; i < size(); i++) {
			if (list[i].equals(element)) {
				throw new IllegalArgumentException();
			}
		}

		if (size() == list.length) {
			growArray();
		}

		for (int i = size() - 1; i >= index; i--) {
			list[i + 1] = list[i];
		}
		list[index] = element;
		size++;
	}

	/**
	 * Grows the actual size of the array
	 */
	@SuppressWarnings("unchecked")
	private void growArray() {
		E[] newList = (E[]) new Object[size * 2];
		for (int i = 0; i < size(); i++) {
			newList[i] = list[i];
		}
		list = newList;
	}

	/**
	 * Removes the elements at the given index
	 * 
	 * @param index index of elements being removed
	 * @return elements at index
	 * @throws IndexOutOfBoundsException if index is less than 0 or greater than
	 *                                   size
	 */
	@Override
	public E remove(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		E oldElement = list[index];
		for (int i = index; i < size() - 1; i++) {
			list[i] = list[i + 1];
		}
		list[size() - 1] = null;
		size--;
		return oldElement;
	}

	/**
	 * Sets the elements at the given index
	 * 
	 * @param index   index of elements being set
	 * @param element being set
	 * @return elements at index
	 * @throws IndexOutOfBoundsException if index is less than 0 or greater than
	 *                                   size
	 * @throws NullPointerException      if element is null
	 * @throws IllegalArgumentException  if element is duplicate
	 */
	@Override
	public E set(int index, E element) {
		if (element == null) {
			throw new NullPointerException();
		}
		E oldElement = get(index);
		for (int i = 0; i < size(); i++) {
			if (list[i].equals(element)) {
				throw new IllegalArgumentException();
			}
		}
		list[index] = element;
		return oldElement;
	}

	/**
	 * Gets the elements at the given index
	 * 
	 * @param index index of elements being got
	 * @return elements at index
	 * @throws IndexOutOfBoundsException if index is less than 0 or greater than
	 *                                   size
	 */
	@Override
	public E get(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		} else {
			return list[index];
		}
	}

	/**
	 * Gets the size of the array list
	 * 
	 * @return size of the array list
	 */
	public int size() {
		return size;
	}
}
