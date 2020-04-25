package coursePlanner.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import coursePlanner.planner.Student;

public class FileProcessor {

	Map<Integer, Student> studentData = new HashMap<>();
	List<Student> students = new ArrayList<Student>();

	AssignCourses assignCourseForStudents = new AssignCourses();

	String outputFilePath = "";

	/**
	 * @param filePath
	 * 
	 *            Extract the data from input file and pass it to assignCourses
	 *            function.
	 */
	public void getStudentDetails(String[] filePath) {
		outputFilePath = filePath[1];

		Scanner sc = null;

		try {

			File file = new File(filePath[0]);
			sc = new Scanner(file);

			while (sc.hasNextLine()) {
				Student newStudent = new Student();

				String[] details = sc.nextLine().split(":");
				int studentID = Integer.parseInt(details[0]);
				newStudent.setStudentId(studentID);

				String[] courses = details[1].split(" ");

				for (String string : courses) {
					newStudent.getCoursePreference().add(string);
				}

				students.add(newStudent);
				studentData.put(studentID, newStudent);

			}
			assignCourseForStudents.assignCourses(outputFilePath, studentData);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != sc) {
				sc.close();
			}

		}
	}

}
