package edu.ncsu.csc216.pack_scheduler.catalog;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * A class representing a catalog that contains a collection of the course
 * object
 */
public class CourseCatalog {

	/** A catalog of courses. */
	private SortedList<Course> catalog;

	/**
	 * Constructs an empty catalog object
	 */
	public CourseCatalog() {
		newCourseCatalog();
	}

	/**
	 * Constructs an empty catalog object
	 */
	public void newCourseCatalog() {
		catalog = new SortedList<Course>();
	}

	/**
	 * Constructs the course catalog by reading in course information from the given
	 * file. Throws an IllegalArgumentException if the file cannot be found.
	 * 
	 * @param fileName file containing list of courses
	 */
	public void loadCoursesFromFile(String fileName) {
		try {
			catalog = CourseRecordIO.readCourseRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}
	}

	/**
	 * Adds a Course to the catalog. Returns true if the course is added and false
	 * if the course is unable to be added because it already exists.
	 * 
	 * @param name         name of course
	 * @param title        title of course
	 * @param section      course section
	 * @param credits      course credits
	 * @param instructorId Id of course instructor
	 * @param meetingDays  days where course meets
	 * @param startTime    start time of course
	 * @param endTime      end time of course
	 * @return true if added or false if duplicate
	 */
	public boolean addCourseToCatalog(String name, String title, String section, int credits, String instructorId,
			String meetingDays, int startTime, int endTime) {
		Course course = new Course(name, title, section, credits, instructorId, meetingDays, startTime, endTime);

		for (int i = 0; i < catalog.size(); i++) {
			Course c = catalog.get(i);
			if (c.getName().equals(name) && c.getSection().equals(section)) {
				return false;
			}
		}
		return catalog.add(course);
	}

	/**
	 * Removes the course with the given name and string from the catalog. Returns
	 * true if the course is removed and false if the course is not in the catalog.
	 * 
	 * @param name    name of course
	 * @param section course section
	 * @return true if removed or false if not in catalog
	 */
	public boolean removeCourseFromCatalog(String name, String section) {
		for (int i = 0; i < catalog.size(); i++) {
			Course c = catalog.get(i);
			if (c.getName().equals(name) && c.getSection().equals(section)) {
				catalog.remove(i);
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets the course with the given name and string from the catalog. Returns null
	 * if the course is not in the catalog.
	 * 
	 * @param name    name of course
	 * @param section course section
	 * @return course the course from the catalog with matching parameters
	 */
	public Course getCourseFromCatalog(String name, String section) {
		for (int i = 0; i < catalog.size(); i++) {
			Course c = catalog.get(i);
			if (c.getName().equals(name) && c.getSection().equals(section)) {
				return c;
			}
		}
		return null;
	}

	/**
	 * Returns all courses in the catalog with a column for name, section, title,
	 * and meeting information.
	 * 
	 * @return String array containing courses name, section, title, and meeting
	 *         information
	 */
	public String[][] getCourseCatalog() {
		String[][] coursecatalog = new String[catalog.size()][4];
		for (int i = 0; i < catalog.size(); i++) {
			Course c = catalog.get(i);
			coursecatalog[i][0] = c.getName();
			coursecatalog[i][1] = c.getSection();
			coursecatalog[i][2] = c.getTitle();
			coursecatalog[i][3] = c.getMeetingString();
		}
		return coursecatalog;
	}

	/**
	 * Saves all courses in the catalog to a file.
	 * 
	 * @param fileName name of file to save catalog to.
	 */
	public void saveCourseCatalog(String fileName) {
		try {
			CourseRecordIO.writeCourseRecords(fileName, catalog);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to write to file " + fileName);
		}
	}

}