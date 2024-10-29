package edu.ncsu.csc216.pack_scheduler.io;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.File;
import java.io.FileInputStream;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * A class representing a student record io
 * 
 * @author Gabe Frain
 * @author Justin Pak
 * @author Bryce Yang
 */
public class StudentRecordIO {
	/**
	 * Will read in Student records from the file represented by the given fileName.
	 * 
	 * @param fileName name of file
	 * @return a sorted list of all students
	 * @throws FileNotFoundException if file cannot be found
	 */
	public static SortedList<Student> readStudentRecords(String fileName) throws FileNotFoundException {
		Scanner scan = new Scanner(new FileInputStream(fileName));
		SortedList<Student> output = new SortedList<Student>();
		while (scan.hasNextLine()) {
			String nextLine = scan.nextLine();
			Scanner scanLine = new Scanner(nextLine);
			scanLine.useDelimiter(",");
			String[] elements = new String[6];
			int i = 0;
			while (scanLine.hasNext()) {
				elements[i] = scanLine.next();
				i++;
			}
			try {
				Student s = new Student(elements[0], elements[1], elements[2], elements[3], elements[4],
						Integer.parseInt(elements[5]));
				output.add(s);
			} catch (IllegalArgumentException e) {
				i++;
			}
			scanLine.close();
		}
		scan.close();
		return output;
	}

	/**
	 * Will write a file output based on an sorted list of students
	 * 
	 * @param fileName         name of output file
	 * @param studentDirectory sorted list of all students
	 * @throws IOException if output file cannot be found
	 */
	public static void writeStudentRecords(String fileName, SortedList<Student> studentDirectory) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < studentDirectory.size(); i++) {
			fileWriter.println(studentDirectory.get(i).toString());
		}

		fileWriter.close();

	}

}
