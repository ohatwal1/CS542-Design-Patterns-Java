package coursePlanner.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import coursePlanner.planner.Student;
import coursePlanner.state.Context;

public class AssignCourses {

	Results result = new Results();
	Context context = new Context();
	
	Map<String, ArrayList<String>> groups = new HashMap<String, ArrayList<String>>();

	final ArrayList<String> group1 = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));
	final ArrayList<String> group2 = new ArrayList<>(Arrays.asList("E", "F", "G", "H"));
	final ArrayList<String> group3 = new ArrayList<>(Arrays.asList("I", "J", "K", "L"));
	final ArrayList<String> group4 = new ArrayList<>(Arrays.asList("M", "N", "O", "P"));
	final ArrayList<String> group5 = new ArrayList<>(Arrays.asList("Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"));

	/**
	 * @param studentData
	 * 
	 *            Get student data and assign courses with satisfying all the
	 *            conditions.
	 */
	public void assignCourses(String outPutFilePath, Map<Integer, Student> studentData) {

		groups.put("group1", group1);
		groups.put("group2", group2);
		groups.put("group3", group3);
		groups.put("group4", group4);
		groups.put("group5", group5);
		ArrayList<String> banned_list = new ArrayList<>();
		StringBuilder sb = new StringBuilder();

		;
		// Get the student data and start processing
		for (Map.Entry<Integer, Student> entry : studentData.entrySet()) {

			Student student = entry.getValue();
			// sb = new StringBuilder();
			while ((!student.getCoursePreference().isEmpty())) {
				if (student.isGraduated() != true) {
					boolean validSub = false;
					String valid_course = "";

					for (int i = 0; i < student.getWaitList().size(); i++) {
						// Get the Course preference from wait list and start processing.
						if ((prerequisite(student.getWaitList().get(i), student.getAssignedCourses(), student)
								&& (!banned_list.contains(student.getWaitList().get(i))))) {
							validSub = true;
							valid_course = student.getWaitList().get(i);
							student.getWaitList().remove(i);
							break;
						} else {
						}
					}
					// Check if the course is valid and it does not belong to group 5
					if (validSub) {

						assign(student, valid_course);
						for (String key : groups.keySet()) {
							if (groups.get(key).contains(valid_course) && key != "group5") {
								banned_list.addAll(groups.get(key));
							} else {
							}
						}
						validSub = false;

					} else {
						// Get the Course preference from user provided input.
						int i = 0;
						while (i < student.getCoursePreference().size()) {
							valid_course = student.getCoursePreference().get(i);
							if (prerequisite(valid_course, student.getAssignedCourses(), student)
									&& (!banned_list.contains(valid_course))) {
								assign(student, valid_course);
								for (String key : groups.keySet()) {
									if (groups.get(key).contains(valid_course) && key != "group5") {
										banned_list.addAll(groups.get(key));
									}
								}
								student.getCoursePreference().remove(i);
								break;

							} else {
								student.getWaitList().add(valid_course);
								student.getCoursePreference().remove(i);
							}
						}
					}
					context.setGradStatus(student);
					if (student.getTotalAssignedCourses() % 3 == 0 || student.isGraduated()) {
						context.updateState(student);
						student.setSemester(student.getSemester() + 1);
						banned_list = new ArrayList<>();
					}
				} else {
					String currSb = student.getStudentId() + ": " + student.getAssignedCourses() + " -- "
							+ student.getCurrentSem() + " " + student.getStateChangeCount() + "\n";
					sb.append(currSb.replace("[", "").replace("]", "").replace(",", ""));
					
					break;

				}
			}
			if (!student.isGraduated()) {
				sb.append(student.getStudentId() + ": Student cannot graduate\n");
			}
		}
		result.writeOutput(sb, outPutFilePath);
	}

	/**
	 * @param course
	 * @param assigned
	 * @param student
	 * @return
	 * 
	 * 		Check if prerequisite condition is satisfy
	 */
	public boolean prerequisite(String course, ArrayList<String> assigned, Student student) {
		ArrayList<String> foundCourse = null;
		for (ArrayList<String> value : groups.values()) {
			if (value.contains(course)) {
				foundCourse = value;
				break;
			}
		}
		if ((foundCourse != null) && (foundCourse.indexOf(course) == 0)) {
			return true;
		}

		if (foundCourse != null) {
			int index = foundCourse.indexOf(course) - 1;
			String prereqCourse = foundCourse.get(index);
			if ((student.getAssignedCourses().contains(prereqCourse))) {
				return true;
			}
		}

		return false;

	}

	/**
	 * @param student
	 * @param course
	 * 
	 *            Assign course the student, add that course to AssignedCourses and
	 *            increase the TotalAssignedCourses count by 1
	 */
	public void assign(Student student, String course) {
		ArrayList<String> foundCourse = null;
		for (ArrayList<String> value : groups.values()) {
			if (value.contains(course)) {
				foundCourse = value;
				break;
			}
		}
		student.setTotalAssignedCourses(student.getTotalAssignedCourses() + 1);
		student.getAssignedCourses().add(course);

	}

}
