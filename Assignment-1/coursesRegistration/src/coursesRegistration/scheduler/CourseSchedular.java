package coursesRegistration.scheduler;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

import coursesRegistration.util.Results;

public class CourseSchedular {

	Map<String, List<Student>> mappings = new HashMap<>();
	Map<String, Course> courseMappings = new HashMap<>();
	Map<Integer, Set<Integer>> assignedCourses = new HashMap<>();

	Queue<Student> fyStudents = new LinkedList<>(); // This queue maintains first come first serve students
	Queue<Student> syStudents = new LinkedList<>();
	Queue<Student> tyStudents = new LinkedList<>();
	StringBuilder sb = new StringBuilder();
	String outputFilePath = "";
	int finalAverage = 0;
	int sumOfStudents = 0;
	int numberofStudents = 0;

	Results results = new Results();

	/**
	 * @param filePath
	 *            Get the Student data from file - Bnumber, Course Preference and
	 *            Year Get the course details from the file - Course name, Capacity
	 *            and Time
	 */
	public void schedule(String[] filePath) {

		// Student validStudent = new Student();
		// Course validCourse = new Course();
		outputFilePath = filePath[2];

		Scanner sc = null;

		try {

			File file = new File(filePath[0]);
			sc = new Scanner(file);

			while (sc.hasNextLine()) {
				Student newStudent = new Student();

				String[] details = sc.nextLine().split(" ");
				int bNumber = Integer.parseInt(details[0]);
				newStudent.setbNumber(bNumber);

				String[] yearDetails = details[1].split("::");
				if (yearDetails[1].equals("FIRST_YEAR")) {
					newStudent.setYear(StudentLevel.FIRST_YEAR);

				} else if (yearDetails[1].equals("SECOND_YEAR")) {
					newStudent.setYear(StudentLevel.SECOND_YEAR);

				} else {

					newStudent.setYear(StudentLevel.THIRD_YEAR);

				}

				String[] courses = yearDetails[0].split(",");

				for (String string : courses) {
					newStudent.getCoursePreferences().add(string.charAt(0));
				}
				if (newStudent.getCoursePreferences().size() != 9) {
					continue;
				}

				if (newStudent.getbNumber() < 100 && newStudent.getbNumber() > 999) {
					continue;
				}

				// Map all the students to their years
				if (newStudent.getYear().equals(StudentLevel.FIRST_YEAR)) {
					fyStudents.add(newStudent);
					List<Student> oldStudents = null;
					if (mappings.containsKey("FY")) {
						oldStudents = mappings.get("FY");
					} else {
						oldStudents = new ArrayList<>();
					}
					oldStudents.add(newStudent);
					mappings.put("FY", oldStudents);
				} else if (newStudent.getYear().equals(StudentLevel.SECOND_YEAR)) {
					syStudents.add(newStudent);
					List<Student> oldStudents = null;
					if (mappings.containsKey("SY")) {
						oldStudents = mappings.get("SY");
					} else {
						oldStudents = new ArrayList<>();
					}
					oldStudents.add(newStudent);
					mappings.put("SY", oldStudents);
				} else {
					tyStudents.add(newStudent);
					List<Student> oldStudents = null;
					if (mappings.containsKey("TY")) {
						oldStudents = mappings.get("TY");
					} else {
						oldStudents = new ArrayList<>();
					}
					oldStudents.add(newStudent);
					mappings.put("TY", oldStudents);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (sc != null) {
				sc.close();
			}

		}

		try {

			File file = new File(filePath[1]);
			sc = new Scanner(file);
			// Map Course name, Capacity and Time
			while (sc.hasNextLine()) {

				Course newCourse = new Course();

				String[] details = sc.nextLine().split(" ");
				String courseName = (details[0]);
				newCourse.setCourseName(courseName);

				String[] capTime = details[1].split(";");
				String[] capacity = capTime[0].split(":");
				newCourse.setCapacity(Integer.parseInt(capacity[1]));

				String[] time = capTime[1].split(":");
				newCourse.setClassTiming(Integer.parseInt(time[1]));

				courseMappings.put(courseName, newCourse);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}

	}

	/**
	 * Get the student details based on the their year and call assign() method Call
	 * writeOutput() method to write the data
	 */
	public void assignCourses() {

		// Check for Third Year Student queue and if its not empty assign course
		while (!tyStudents.isEmpty()) {
			Student student = tyStudents.poll();
			assign(student);

		}
		// Check for Second Year Student queue and if its not empty assign course
		while (!syStudents.isEmpty()) {
			Student student = syStudents.poll();
			assign(student);

		}
		// Check for First Year Student queue and if its not empty assign course
		while (!fyStudents.isEmpty()) {
			Student student = fyStudents.poll();
			assign(student);

		}

		finalAverage = sumOfStudents / numberofStudents;
		sb.append("\n");
		sb.append("AverageSatisfactionRating =  " + finalAverage);
		results.writeOutput(sb, outputFilePath);
	}

	/**
	 * @param student
	 *            Assign courses to the students based on year and their course
	 *            preferences Provide Satisfaction rating
	 */
	public void assign(Student student) {
		int sum = 0;
		numberofStudents = numberofStudents + 1;
		sb.append(student.getbNumber() + " : ");
		for (char c : student.getCoursePreferences()) {
			int satisfactionRating = 9;
			Course course = courseMappings.get(String.valueOf(c));
			if (course.getCapacity() > 0 && student.getTotalAssignedCourses() < 3) {
				Set<Integer> addedCourse = assignedCourses.get(student.getbNumber());
				if (null == addedCourse) {
					addedCourse = new HashSet<Integer>();
				}
				if (!addedCourse.contains(course.getClassTiming())) {
					course.setCapacity(course.getCapacity() - 1);

					satisfactionRating = satisfactionRating - student.getCoursePreferences().indexOf(c);

					student.setTotalAssignedCourses(student.getTotalAssignedCourses() + 1);
					sum = sum + satisfactionRating;
					addedCourse.add(course.getClassTiming());
					assignedCourses.put(student.getbNumber(), addedCourse);

					// Append results to String Builder object
					sb.append(c);
					if (student.getTotalAssignedCourses() != 3) {
						sb.append(",");
					}
				}

			}

		}
		sb.append(" SatisfactionRating =  " + sum);
		sumOfStudents = sumOfStudents + sum;

		sb.append("\n");
	}
}
