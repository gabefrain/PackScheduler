package edu.ncsu.csc216.pack_scheduler.user.schedule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc216.pack_scheduler.course.Course;
/**
 * Tests the functionality of the Schedule class.
 * 
 * @author Rujul Waval
 */
public class ScheduleTest {
	
	/** Schedule object used for testing. */
    private Schedule schedule;
    
    /** First course used for testing. */
    private Course course1;
    
    /** 
     * Sets up the test environment by initializing the Schedule and Course objects 
     * before each test.
     */
    @Before
    public void setUp() {
        schedule = new Schedule();
        course1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "jdoe", "MW", 930, 1045);
    }
    
    /** 
     * Tests the constructor of Schedule. 
     */
    @Test
    public void testConstructor() {
        assertEquals("My Schedule", schedule.getTitle());
        assertEquals(0, schedule.getScheduledCourses().length);
    }
    /**
     * Tests adding a course to the schedule.
     */
    @Test
    public void testAddCourseToSchedule() {
        assertTrue(schedule.addCourseToSchedule(course1));
        assertEquals(1, schedule.getScheduledCourses().length);
    }
    
    /**
     * Tests adding a duplicate course to the schedule.
     */
    @Test
    public void testAddDuplicateCourse() {
        schedule.addCourseToSchedule(course1);
        try {
            schedule.addCourseToSchedule(course1);
            fail("Expected IllegalArgumentException was not thrown.");
        } catch (IllegalArgumentException e) {
            assertEquals("You are already enrolled in " + course1.getName(), e.getMessage());
        }
    }

    /**
     * Tests removing a course from the schedule.
     */
    @Test
    public void testRemoveCourseFromSchedule() {
        schedule.addCourseToSchedule(course1);
        assertTrue(schedule.removeCourseFromSchedule(course1));
        assertEquals(0, schedule.getScheduledCourses().length);
    }
    
    /**
     * Tests resetting the schedule.
     */
    @Test
    public void testResetSchedule() {
        schedule.addCourseToSchedule(course1);
        schedule.resetSchedule();
        assertEquals("My Schedule", schedule.getTitle());
        assertEquals(0, schedule.getScheduledCourses().length);
    }
    
    /**
     * Tests setting the title of the schedule.
     */
    @Test
    public void testSetTitle() {
        schedule.setTitle("Fall 2024");
        assertEquals("Fall 2024", schedule.getTitle());
    }
    
    /**
     * Tests that setting a null title throws an exception.
     */
    @Test
    public void testSetTitleNull() {
        try {
            schedule.setTitle(null);
            fail("Expected IllegalArgumentException was not thrown.");
        } catch (IllegalArgumentException e) {
            assertEquals("Title cannot be null.", e.getMessage());
        }
    }

}
